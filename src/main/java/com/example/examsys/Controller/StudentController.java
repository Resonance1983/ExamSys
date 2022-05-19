package com.example.examsys.Controller;


import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.Student;
import com.example.examsys.Services.StudentServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@RestController
@RequestMapping("student")
@ResponseBody
public class StudentController {
    @Autowired
    private StudentServices studentServices;

    @JwtToken(requirePower = 3)
    @PostMapping(value="addStudent",produces = "application/json;charset=UTF-8")
    public ResponseData addStudent(@RequestBody StudentDTO studentDTO){
        ResponseData rsp = new ResponseData();
        try{
            studentServices.addStudent(studentDTO);
            rsp.setRspData(studentDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 3)
    @DeleteMapping("deleteStudent/{id}")
    public ResponseData deleteStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            studentServices.deleteStudentById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 1)
    @GetMapping("findStudent/{id}")
    public ResponseData findStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Student student = studentServices.findStudentById(id);
            rsp.setRspData(student);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 1)
    @PutMapping(value = "updateStudent",produces = "application/json;charset=UTF-8")
    public ResponseData updateStudent(@RequestBody StudentDTO studentDTO, HttpServletRequest httpServletRequest){
        ResponseData rsp = new ResponseData();
        try {
            String token = httpServletRequest.getHeader("token");
            if(studentDTO.getId() == Long.parseLong(JwtUtil.getUserId(token))){
                studentServices.updateStudent(studentDTO);
                rsp.setRspData(studentDTO);
            }else{
                rsp.setFailed();
                rsp.setRspMsg("非修改用户");
            }
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 3)
    @GetMapping("fillStudent")
    public void fillStudent(){
        studentServices.fillStudent();
    }

}