package com.example.examsys.Services.implement;

import com.example.examsys.DTO.TeacherDTO;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Repository.TeacherRepository;
import com.example.examsys.Services.TeacherServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServicesImplement implements TeacherServices {
    @Autowired
    private TeacherRepository tr;

    @Cacheable(key = "#p0.getId()", value = "TeacherID#2")
    public Teacher addTeacher(TeacherDTO teacherDTO) {
        try {
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacherDTO, teacher);
            tr.save(teacher);
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CacheEvict(key = "#p0", value = "TeacherID")
    public boolean deleteTeacherById(Long id) {
        try {
            tr.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0", value = "TeacherID#2")
    public Teacher findTeacherById(Long id) {
        try {
            Teacher teacher = tr.findById(id).get();
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()", value = "TeacherID#2")
    public boolean updateTeacher(TeacherDTO teacherDTO) {
        try {
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacherDTO, teacher);
            tr.save(teacher);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Teacher> findAllTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>(tr.findAll());
        return teachers;
    }

    public void fillTeacher() {
        Teacher a = new Teacher();
        a.setName("zgy");
        a.setId(31901032L);
        a.setPassWord("31901032");
        tr.save(a);
    }
}