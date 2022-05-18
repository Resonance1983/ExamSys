package com.example.examsys.Controller;

import com.example.examsys.Services.LoginServices;
import com.example.examsys.Services.StudentServices;
import com.example.examsys.Services.TeacherServices;
import com.example.examsys.Services.AdminServices;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@ResponseBody
public class LoginController {
    @Autowired
    private StudentServices studentServices;
    @Autowired
    private TeacherServices teacherServices;
    @Autowired
    private AdminServices adminServices;
    @Autowired
    private LoginServices loginServices;

    @GetMapping("StudentLogin/{id}/{password}")
    public ResponseData studentLogin(@PathVariable("id") Long studentID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentLogin(studentID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @GetMapping("TeacherLogin/{id}/{password}")
    public ResponseData teacherLogin(@PathVariable("id") Long teacherID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherLogin(teacherID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }
    @GetMapping("AdminLogin/{id}/{password}")
    public ResponseData adminLogin(@PathVariable("id") Long adminID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.adminLogin(adminID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @PutMapping("StudentPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData studentModify(@PathVariable("id") Long studentID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentModify(studentID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @PutMapping("TeacherPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData teacherModify(@PathVariable("id") Long teacherID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherModify(teacherID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }
    @PutMapping("AdminPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData adminModify(@PathVariable("id") Long adminID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.adminModify(adminID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }
}
