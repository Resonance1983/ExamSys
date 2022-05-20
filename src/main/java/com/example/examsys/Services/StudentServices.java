package com.example.examsys.Services;

import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Student;

public interface StudentServices {
    Student addStudent(StudentDTO studentDTO);

    boolean deleteStudentById(Long id);

    Student findStudentById(Long id);

    boolean updateStudent(StudentDTO studentDTO);

    void fillStudent();
}
