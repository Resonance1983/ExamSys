package com.example.examsys.Services;

import com.example.examsys.DTO.TeacherDTO;
import com.example.examsys.Entity.Teacher;

import java.util.ArrayList;

public interface TeacherServices {
    Teacher addTeacher(TeacherDTO teacherDTO);

    boolean deleteTeacherById(Long id);

    Teacher findTeacherById(Long id);

    boolean updateTeacher(TeacherDTO teacherDTO);

    ArrayList<Teacher> findAllTeachers();

    void fillTeacher();
}
