package com.example.examsys.Services.implement;

import com.example.examsys.Entity.Admin;
import com.example.examsys.Entity.Student;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Repository.AdminRepository;
import com.example.examsys.Repository.StudentRepository;
import com.example.examsys.Repository.TeacherRepository;
import com.example.examsys.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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

    public boolean studentLogin(long studentID, String password) {
        try {
            Student user = sr.findById(studentID).get();
            return user.getId() == studentID && user.getPassWord().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean teacherLogin(long teacherID, String password) {
        try {
            Teacher user = tr.findById(teacherID).get();
            return user.getId() == teacherID && user.getPassWord().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean adminLogin(long adminID, String password) {
        try {
            Admin user = ar.findById(adminID).get();
            return user.getId() == adminID && user.getPassWord().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //    @CacheEvict(key = "#p0+'-'+#p1")
//    @Cacheable(key = "#p0+'-'+#p2", value = "stuLogin#4")
    public boolean studentModify(long studentID, String oldpassword, String newpass1, String newpass2) {
        try {
            Student user = sr.findById(studentID).get();
            if (user.getId() == studentID && !user.getPassWord().equals(oldpassword)) {
                if (newpass1.equals(newpass2)) {
                    user.setPassWord(newpass1);
                    sr.save(user);
                    return true;
                }
                return false;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean teacherModify(long teacherID, String oldpassword, String newpass1, String newpass2) {
        try {
            Teacher user = tr.findById(teacherID).get();
            if (user.getId() == teacherID && !user.getPassWord().equals(oldpassword)) {
                if (newpass1.equals(newpass2)) {
                    user.setPassWord(newpass1);
                    tr.save(user);
                    return true;
                }
                return false;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    @CacheEvict(key = "#p0+'-'+#p1")
//    @Cacheable(key = "#p0+'-'+#p2", value = "adminLogin#2")
//    public boolean adminModify(long adminID, String oldpassword, String newpass1, String newpass2) {
//
//        Admin user = ar.findById(adminID).get();
//        if (user.getId() == adminID && !user.getPassWord().equals(oldpassword)) {
//            if (newpass1.equals(newpass2)) {
//                user.setPassWord(newpass1);
//                return true;
//            }
//            return false;
//        } else
//            return false;
//    }
}
