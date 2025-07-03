package com.student.main.services;

import com.student.main.entities.Student;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IStudent {

    static<T> Boolean isNullOrEmpty(T input){
        if(input == null)
            return true;
        return input.toString().isEmpty() || input.toString().isBlank();
    }


    String createOrUpdate(Student studentReq, @AuthenticationPrincipal UserDetails user);

    List<Student> getAllStudents();

    void deleteRecord(List<String> studentIds);
}
