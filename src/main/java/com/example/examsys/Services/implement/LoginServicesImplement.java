package com.example.examsys.Services.implement;

import com.example.examsys.Entity.Student;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Entity.Admin;
import com.example.examsys.Repository.StudentRepository;
import com.example.examsys.Repository.TeacherRepository;
import com.example.examsys.Repository.AdminRepository;
import com.example.examsys.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "LoginService")
public class LoginServicesImplement implements LoginServices {
    @Autowired
    private StudentRepository sr;
    @Autowired
    private TeacherRepository tr;
    @Autowired
    private AdminRepository ar;

    @Cacheable(key = "#p0+'-'+#p1",value = "studentLogin#2")
    public boolean studentLogin(long studentID,String password){
        Student user =sr.findById(studentID).get();
        if(user.getId()==studentID && user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    @Cacheable(key = "#p0+'-'+#p1",value = "teacherLogin#6")
    public boolean teacherLogin(long teacherID,String password){
        Teacher user =tr.findById(teacherID).get();
        if(user.getId()==teacherID && user.getPassWord().equals(password))
            return true;
        else
            return false;
    }
    @Cacheable(key = "#p0+'-'+#p1",value = "adminLogin#4")
    public boolean adminLogin(long adminID,String password){
        Admin user =ar.findById(adminID).get();
        if(user.getId()==adminID && user.getPassWord().equals(password))
            return true;
        else
            return false;
    }

    @CacheEvict(key = "#p0+'-'+#p1")
    @Cacheable(key = "#p0+'-'+#p2",value = "stuLogin#2")
    public boolean studentModify(long studentID,String oldpassword,String newpass1,String newpass2){
        Student user =sr.findById(studentID).get();
        if(user.getId()==studentID && user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }

    @CacheEvict(key = "#p0+'-'+#p1")
    @Cacheable(key = "#p0+'-'+#p2",value = "teacherLogin#6")
    public boolean teacherModify(long teacherID,String oldpassword,String newpass1,String newpass2){
        Teacher user = tr.findById(teacherID).get();
        if(user.getId()==teacherID && user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }
    @CacheEvict(key = "#p0+'-'+#p1")
    @Cacheable(key = "#p0+'-'+#p2",value = "adminLogin#4")
    public boolean adminModify(long adminID,String oldpassword,String newpass1,String newpass2){
        Admin user = ar.findById(adminID).get();
        if(user.getId()==adminID && user.getPassWord().equals(oldpassword)){
            if (newpass1.equals(newpass2)) {
                user.setPassWord(newpass1);
                return true;
            }
            return false;
        }else
            return false;
    }
}
