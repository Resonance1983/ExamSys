package com.example.examsys.Controller;

import com.example.examsys.Services.LoginServices;
import com.example.examsys.Services.StudentServices;
import com.example.examsys.Services.TeacherServices;
import com.example.examsys.Services.AdminServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@ResponseBody
@Api(tags = "登录控制器")
public class LoginController {
    @Autowired
    private LoginServices loginServices;

    @ApiOperation(value = "学生登录")
    @GetMapping("StudentLogin/{id}/{password}")
    public ResponseData studentLogin(@PathVariable("id") Long studentID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentLogin(studentID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }else{
            String token = JwtUtil.sign(studentID.toString(),"student");
            rsp.setRspData(token);
        }

        return rsp;
    }

    @ApiOperation(value = "老师登录")
    @GetMapping("TeacherLogin/{id}/{password}")
    public ResponseData teacherLogin(@PathVariable("id") Long teacherID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherLogin(teacherID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }else{
            String token = JwtUtil.sign(teacherID.toString(),"teacher");
            rsp.setRspData(token);
        }

        return rsp;
    }

    @ApiOperation(value = "管理员登录")
    @GetMapping("AdminLogin/{id}/{password}")
    public ResponseData adminLogin(@PathVariable("id") Long adminID,@PathVariable("password") String password){
        ResponseData rsp = new ResponseData();
        if(!loginServices.adminLogin(adminID,password)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }else{
            String token = JwtUtil.sign(adminID.toString(),"admin");
            rsp.setRspData(token);
        }

        return rsp;
    }

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "学生密码修改")
    @PutMapping("StudentPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData studentModify(@PathVariable("id") Long studentID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.studentModify(studentID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "老师密码修改")
    @PutMapping("TeacherPassModify/{id}/{oldpass}/{newpass}")
    public ResponseData teacherModify(@PathVariable("id") Long teacherID,@PathVariable("oldpass") String oldpassword,@PathVariable("newpass") String newpass1,String newpass2){
        ResponseData rsp = new ResponseData();
        if(!loginServices.teacherModify(teacherID,oldpassword,newpass1,newpass2)){
            rsp.setFailed();
            rsp.setRspData(new Boolean(Boolean.FALSE));
        }

        return rsp;
    }

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "管理员密码修改")
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