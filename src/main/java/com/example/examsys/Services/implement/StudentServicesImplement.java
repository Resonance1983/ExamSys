package com.example.examsys.Services.implement;

import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Admin;
import com.example.examsys.Entity.Student;
import com.example.examsys.Repository.StudentRepository;
import com.example.examsys.Services.StudentServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentServicesImplement implements StudentServices {
    @Autowired
    private StudentRepository sr;

    @Cacheable(key = "#p0.getId()",value = "StudentID#2")
    public Student addStudent(StudentDTO studentDTO){
        try {
            Student student = new Student();
            BeanUtils.copyProperties(studentDTO, student);
            sr.save(student);
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CacheEvict(key = "#p0",value = "StudentID")
    public boolean deleteStudentById(Long id){
        try {
            sr.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0",value = "StudentID#2")
    public Student findStudentById(Long id){
        try {
            Student student = sr.findById(id).get();
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()",value = "StudentID#2")
    public boolean updateStudent(StudentDTO studentDTO){
        try {
            Student student = new Student();
            BeanUtils.copyProperties(studentDTO,student);
            sr.save(student);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void fillStudent(){
        Student a = new Student();
        a.setName("zgy");
        a.setId(31901032L);
        a.setPassWord("31901032");
        sr.save(a);
    }
}