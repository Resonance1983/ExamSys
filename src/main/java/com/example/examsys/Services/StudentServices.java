package com.example.examsys.Services;

import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Student;

public interface StudentServices {
    public Student addStudent(StudentDTO studentDTO);
    public boolean deleteStudentById(Long id);
    public Student findStudentById(Long id);
    public boolean updateStudent(StudentDTO studentDTO);
    public void fillStudent();
}
