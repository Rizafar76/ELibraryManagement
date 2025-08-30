package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.dtos.GetStudentResponse;
import com.example.ELibraryManagement.models.Authority;
import com.example.ELibraryManagement.models.Student;
import com.example.ELibraryManagement.models.StudentStatus;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.repositories.StudentRedisRepository;
import com.example.ELibraryManagement.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
        * */

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
        }
        return GetStudentResponse.builder().student(student).build();
    }

    public GetStudentResponse updateStudent(Student student, Long id) {

        Student existingStudent = this.studentRepository.findById(id).orElse(null);

        Student updatedStudent = this.merge(existingStudent, student);

        Student s = this.studentRepository.save(updatedStudent);

        return GetStudentResponse.builder().student(updatedStudent).build();

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
