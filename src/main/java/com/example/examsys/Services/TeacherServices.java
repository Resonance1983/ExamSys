package com.example.examsys.Services;

import com.example.examsys.DTO.TeacherDTO;
import com.example.examsys.Entity.Teacher;

public interface TeacherServices {
    public Teacher addTeacher(TeacherDTO teacherDTO);
    public boolean deleteTeacherById(Long id);
    public Teacher findTeacherById(Long id);
    public boolean updateTeacher(TeacherDTO teacherDTO);
    public void fillTeacher();
}
