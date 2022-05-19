package com.example.examsys.Controller;


import com.example.examsys.DTO.TeacherDTO;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Services.TeacherServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher")
@ResponseBody
public class TeacherController {
    @Autowired
    private TeacherServices teacherServices;

    @JwtToken(requirePower = 3)
    @PostMapping(value="addTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData addTeacher(@RequestBody TeacherDTO teacherDTO){
        ResponseData rsp = new ResponseData();
        try{
            teacherServices.addTeacher(teacherDTO);
            rsp.setRspData(teacherDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 3)
    @DeleteMapping("deleteTeacher/{id}")
    public ResponseData deleteTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            teacherServices.deleteTeacherById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 1)
    @GetMapping("findTeacher/{id}")
    public ResponseData findTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Teacher teacher = teacherServices.findTeacherById(id);
            rsp.setRspData(teacher);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @PutMapping(value = "updateTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData updateTeacher(@RequestBody TeacherDTO teacherDTO){
        ResponseData rsp = new ResponseData();
        try {
            teacherServices.updateTeacher(teacherDTO);
            rsp.setRspData(teacherDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 3)
    @GetMapping("fillTeacher")
    public void fillTeacher(){
        teacherServices.fillTeacher();
    }

}