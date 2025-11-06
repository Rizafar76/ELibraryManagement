package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.dtos.GetStudentResponse;
import com.example.ELibraryManagement.models.Authority;
import com.example.ELibraryManagement.models.Student;
import com.example.ELibraryManagement.models.StudentStatus;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.repositories.StudentRedisRepository;
import com.example.ELibraryManagement.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    UserService userService;

    @Autowired
    StudentRedisRepository studentRedisRepository;

    public Long createStudent(Student student) {

        User user = this.userService.createUser(student.getUser(), Authority.STUDENT);
        student.setUser(user);
        return this.studentRepository.save(student).getId();
        /*
        * 1. encode password
        * 2. add authority
        * 3. create user
        * 4. attach student and user
        * 5. save student and return id
        */

    }

    private final ExecutorService executor = Executors.newFixedThreadPool(5);


    public GetStudentResponse getStudent(Long id) {

        Student student = this.studentRedisRepository.get(id);

        if(student!=null){
            return GetStudentResponse
                    .builder()
                    .student(student)
                    .build();
        }

        student = this.studentRepository.findById(id).orElse(null);

        if(student!=null) {                //parallel thread
            Student s2 = student;
            executor.submit(() -> this.studentRedisRepository.add(s2));
            System.out.println("Current Thread" + Thread.currentThread().getName());
        }
        return GetStudentResponse.builder().student(student).build();
    }

    public GetStudentResponse updateStudent(Student student, Long id) {


        Student existingStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        Student updatedStudent = this.merge(existingStudent, student);

        Student saveUpdatedStudent = this.studentRepository.save(updatedStudent);

        return GetStudentResponse.builder().student(saveUpdatedStudent).build();

    }


    private Student merge(Student existing, Student incoming) {
        JSONObject incomingStudent = mapper.convertValue(incoming, JSONObject.class);
        JSONObject savedStudent = mapper.convertValue(existing, JSONObject.class);

        Iterator it = incomingStudent.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (incomingStudent.get(key) != null) {
                savedStudent.put(key, incomingStudent.get(key));
            }

            }


        Map<String,Object> users= (Map<String, Object>) savedStudent.get("user");

        System.out.println(users);

        List<Map<String,Object>> m= (List<Map<String, Object>>) users.get("authorities");
        Map<String, Object> m1=m.get(0);

        users.put("authorities",m1.get("authority"));

        savedStudent.put("user",users);
        Student updatedStudent = mapper.convertValue(savedStudent, Student.class);
        return updatedStudent;
    }

    public void deactivate(Long id) {
        Student student = this.studentRepository.findById(id).orElse(null);
        if (student != null) {
            this.studentRepository.deactivate(student.getId(), StudentStatus.INACTIVE);
        }
    }
}
