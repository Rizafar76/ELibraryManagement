package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


/*
* student ::->  1->student  -> std::1 -> student
* book   ::->    1->book    -> book::1 -> book
* transaction ::-> 1 ->transaction -> txn::1 -> transaction
*
*
*
* */

@Repository
public class StudentRedisRepository {

    private static final String STUDENT_KEY_PREFIX = "std::";
    private static final Long STUDENT_KEY_EXPIRY = 3600L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void add(Student student) {
        String key = getKey(student.getId());
        this.redisTemplate.opsForValue().set(key,student,STUDENT_KEY_EXPIRY, TimeUnit.SECONDS);
    }

    public Student get(Long studentId) {
        String key = getKey(studentId);
        return (Student) this.redisTemplate.opsForValue().get(key);
    }

    public String getKey(Long studentId) {
        return STUDENT_KEY_PREFIX + studentId;
    }



}
