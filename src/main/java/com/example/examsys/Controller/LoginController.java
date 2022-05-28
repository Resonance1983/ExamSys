package com.example.examsys.Controller;

import com.example.examsys.Services.LoginServices;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("login")
@ResponseBody
@Api(tags = "登录控制器")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginServices loginServices;

    @ApiOperation(value = "学生登录")
    @GetMapping("StudentLogin/{id}/{password}")
    public Callable<ResponseData> studentLogin(@PathVariable("id") Long studentID, @PathVariable("password") String password) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    if (!loginServices.studentLogin(studentID, password)) {
                        rsp.setFailed();
                        rsp.setRspData(new Boolean(Boolean.FALSE));
                    } else {
                        String token = JwtUtil.sign(studentID.toString(), "student");
                        rsp.setRspData(token);
                    }
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "老师登录")
    @GetMapping("TeacherLogin/{id}/{password}")
    public Callable<ResponseData> teacherLogin(@PathVariable("id") Long teacherID, @PathVariable("password") String password) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    if (!loginServices.teacherLogin(teacherID, password)) {
                        rsp.setFailed();
                        rsp.setRspData(new Boolean(Boolean.FALSE));
                    } else {
                        String token = JwtUtil.sign(teacherID.toString(), "teacher");
                        rsp.setRspData(token);
                    }
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "管理员登录")
    @GetMapping("AdminLogin/{id}/{password}")
    public Callable<ResponseData> adminLogin(@PathVariable("id") Long adminID, @PathVariable("password") String password) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    if (!loginServices.adminLogin(adminID, password)) {
                        rsp.setFailed();
                        rsp.setRspData(new Boolean(Boolean.FALSE));
                    } else {
                        String token = JwtUtil.sign(adminID.toString(), "admin");
                        rsp.setRspData(token);
                    }
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "学生密码修改")
    @PutMapping("StudentPassModify/{id}/{oldpass}/{newpass1}/{newpass2}")
    public Callable<ResponseData> studentModify(@PathVariable("id") Long studentID, @PathVariable("oldpass") String oldpassword, @PathVariable("newpass1") String newpass1, @PathVariable("newpass2") String newpass2) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    if (!loginServices.studentModify(studentID, oldpassword, newpass1, newpass2)) {
                        logger.info(oldpassword + " " + newpass1 + " " + newpass2);
                        rsp.setFailed();
                        rsp.setRspData(new Boolean(Boolean.FALSE));
                    }
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "老师密码修改")
    @PutMapping("TeacherPassModify/{id}/{oldpass}/{newpass1}/{newpass2}")
    public Callable<ResponseData> teacherModify(@PathVariable("id") Long teacherID, @PathVariable("oldpass") String oldpassword, @PathVariable("newpass1") String newpass1, @PathVariable("newpass2") String newpass2) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    if (!loginServices.teacherModify(teacherID, oldpassword, newpass1, newpass2)) {
                        logger.info(oldpassword + " " + newpass1 + " " + newpass2);
                        rsp.setFailed();
                        rsp.setRspData(new Boolean(Boolean.FALSE));
                    }
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

//    @ApiOperation(value = "管理员密码修改")
//    @PutMapping("AdminPassModify/{id}/{oldpass}/{newpass}")
//    public Callable<ResponseData> adminModify(@PathVariable("id") Long adminID, @PathVariable("oldpass") String oldpassword, @PathVariable("newpass") String newpass1, String newpass2) {
//        return new Callable<ResponseData>() {
//            @Override
//            public ResponseData call() throws Exception {
//                ResponseData rsp = new ResponseData();
//                System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
//                if (!loginServices.adminModify(adminID, oldpassword, newpass1, newpass2)) {
//                    rsp.setFailed();
//                    rsp.setRspData(new Boolean(Boolean.FALSE));
//                }
//                return rsp;
//            }
//        };
//    }

}