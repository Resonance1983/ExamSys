package com.example.examsys.Controller;


import com.example.examsys.DTO.TeacherDTO;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Services.TeacherServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("teacher")
@ResponseBody
@Api(tags = "老师管理控制器")
public class TeacherController {
    @Autowired
    private TeacherServices teacherServices;

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "添加老师")
    @PostMapping(value="addTeacher",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addTeacher(@RequestBody TeacherDTO teacherDTO){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    teacherServices.addTeacher(teacherDTO);
                    rsp.setRspData(teacherDTO);
                }catch (Exception e){
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "id删除老师")
    @DeleteMapping("deleteTeacher/{id}")
    public Callable<ResponseData> deleteTeacherById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    teacherServices.deleteTeacherById(id);
                    rsp.setRspData(new Boolean(Boolean.TRUE));
                }catch (Exception e){
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "id寻找老师")
    @GetMapping("findTeacher/{id}")
    public Callable<ResponseData> findTeacherById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(new TeacherDTO(teacherServices.findTeacherById(id)));
                }catch (Exception e){
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "更新老师信息")
    @PutMapping(value = "updateTeacher",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateTeacher(@RequestBody TeacherDTO teacherDTO, HttpServletRequest httpServletRequest){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    String token = httpServletRequest.getHeader("token");
                    if(teacherDTO.getId() == Long.parseLong(JwtUtil.getUserId(token))){
                        teacherServices.updateTeacher(teacherDTO);
                        rsp.setRspData(teacherDTO);
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
        };
    }

    @ApiOperation(value = "填充老师（测试用）")
    @GetMapping("fillTeacher")
    public void fillTeacher(){
        teacherServices.fillTeacher();
    }

}