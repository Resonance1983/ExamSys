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

    @Cacheable(key = "#p0.getId()", value = "TeacherID#5")
    public Teacher addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        tr.save(teacher);
        return teacher;
    }

    @CacheEvict(key = "#p0", value = "TeacherID")
    public boolean deleteTeacherById(Long id) {
        tr.deleteById(id);
        return true;
    }

    @Cacheable(key = "#p0", value = "TeacherID#5")
    public Teacher findTeacherById(Long id) {
        Teacher teacher = tr.findById(id).get();
        return teacher;
    }

    @CachePut(key = "#p0.getId()", value = "TeacherID#5")
    public boolean updateTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        tr.save(teacher);
        return true;
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