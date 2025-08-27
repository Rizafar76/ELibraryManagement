package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.Book;
import com.example.ELibraryManagement.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class BookRedisRepository {
    private static final String BOOK_KEY_PREFIX = "book::";
    private static final Long BOOK_KEY_EXPIRY = 3600L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void add(Book book) {
        String key = getKey(book.getId());
        this.redisTemplate.opsForValue().set(key,book,BOOK_KEY_EXPIRY, TimeUnit.SECONDS);
    }

    public Book get(Long bookId) {
        String key = getKey(bookId);
        return (Book) this.redisTemplate.opsForValue().get(key);
    }

    public String getKey(Long bookId) {
        return BOOK_KEY_PREFIX + bookId;
    }
}
