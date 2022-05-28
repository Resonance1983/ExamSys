package com.example.examsys.Controller;


import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Student;
import com.example.examsys.Services.StudentServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("student")
@ResponseBody
@Api(tags = "学生管理控制器")
public class StudentController {
    @Autowired
    private StudentServices studentServices;
    @Autowired
    private AmqpTemplate mqService;

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "添加学生")
    @PostMapping(value = "addStudent", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addStudent(@RequestBody StudentDTO studentDTO) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "addStudent");
                    //autoid切面会改变studentDTO的属性所以重新构造一遍
                    rsp.setRspData(new StudentDTO(studentServices.addStudent(studentDTO)));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "id删除学生")
    @DeleteMapping("deleteStudent/{id}")
    public Callable<ResponseData> deleteStudentById(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "deleteStudent");
                    studentServices.deleteStudentById(id);
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

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "id寻找学生")
    @GetMapping("findStudent/{id}")
    public Callable<ResponseData> findStudentById(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "findStudent");
                    rsp.setRspData(new StudentDTO(studentServices.findStudentById(id)));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "修改学生信息")
    @PutMapping(value = "updateStudent", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateStudent(@RequestBody StudentDTO studentDTO, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    String token = httpServletRequest.getHeader("token");
                    if (studentDTO.getId() == Long.parseLong(JwtUtil.getUserId(token)) || JwtUtil.getPower(token) > 1) {
                        studentServices.updateStudent(studentDTO);
                        rsp.setRspData(studentDTO);
                    } else {
                        rsp.setFailed();
                        rsp.setRspMsg("非修改用户");
                    }
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "updateStudent");
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "寻找所有学生")
    @GetMapping("findAllStudents")
    public Callable<ResponseData> findAllStudents() {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "findAllStudents");
                    ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
                    ArrayList<Student> students = studentServices.findAllStudents();
                    for (Student student : students) {
                        studentDTOS.add(new StudentDTO(student));
                    }
                    rsp.setRspData(studentDTOS);
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @PostMapping("fillStudent")
    public void fillStudent() {
        studentServices.fillStudent();
    }

}