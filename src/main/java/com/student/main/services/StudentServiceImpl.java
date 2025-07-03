package com.student.main.services;

import com.student.main.entities.Student;
import com.student.main.repositories.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudent{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String createOrUpdate(Student studentReq, @AuthenticationPrincipal UserDetails user) {
        if(!IStudent.isNullOrEmpty(studentReq.getId())){
            //update the existing object
            Student student = studentRepository.findById(studentReq.getId()).get();
            BeanUtils.copyProperties(studentReq, student, "createdBy", "createdOn");
            student.setUpdatedBy(user.getUsername());
            studentRepository.save(student);
            return "Data updated successfully!";
        }
        //create new student record
        studentReq.setCreatedBy(user.getUsername());
        studentRepository.save(studentReq);
        return "Student record added successfully!";
    }

    @Override
    public List<Student> getAllStudents() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdBy", "updatedBy");
        return studentRepository.findAll(sort);
    }

    @Override
    public void deleteRecord(List<String> studentIds) {
        studentRepository.deleteAllByIdInBatch(studentIds);
    }
}
