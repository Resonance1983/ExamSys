package com.example.examsys.Services;

import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Student;

import java.util.ArrayList;

public interface StudentServices {
    Student addStudent(StudentDTO studentDTO);

    boolean deleteStudentById(Long id);

    Student findStudentById(Long id);

    boolean updateStudent(StudentDTO studentDTO);

    ArrayList<Student> findAllStudents();

    void fillStudent();
}
