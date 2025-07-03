package com.student.main.controllers;


import com.student.main.entities.Student;
import com.student.main.services.IStudent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student Controller")
@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "${frontend.url}", maxAge = 3600, allowCredentials = "true")//For controller level cors fix
public class StudentController {

    @Autowired
    private IStudent iStudent;

    @PostMapping("/add-student")
    @Operation(summary = "Create and update Student")
    @ApiResponse(responseCode = "200 or 201")
    public String createStudent(@RequestBody Student studentReq, @AuthenticationPrincipal UserDetails user){
        return iStudent.createOrUpdate(studentReq, user);
    }

    @GetMapping("/students")
    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200")
    public List<Student> getAllStudents(){
        return iStudent.getAllStudents();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete student's record from table")
    @ApiResponse(responseCode = "200")
    public void deleteRecords(@RequestBody List<String> studentIds){
        iStudent.deleteRecord(studentIds);
    }

    @GetMapping("/")
    @Operation(summary = "For Test")
    @ApiResponse(responseCode = "200")
    public String getHealth(){
        return "up and running";
    }

}
