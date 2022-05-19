package com.example.examsys.Controller;


import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;
import com.example.examsys.Services.TestServices;
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
@RequestMapping("test")
@ResponseBody
@Api(tags = "考试管理控制器")
public class TestController {
    @Autowired
    private TestServices testServices;

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "发布考试")
    @PostMapping(value="addTest",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addTest(@RequestBody TestDTO testDTO){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    testServices.addTest(testDTO);
                    rsp.setRspData(testDTO);
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
    @ApiOperation(value = "id删除考试")
    @DeleteMapping("deleteTest/{id}")
    public Callable<ResponseData> deleteTestById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    testServices.deleteTestById(id);
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
    @ApiOperation(value = "id寻找考试，学生不能获得考试问题信息")
    @GetMapping("findTest/{id}")
    public Callable<ResponseData> findTestById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(new TestDTO(testServices.findTestById(id)));
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
    @ApiOperation(value = "修改考试信息")
    @PutMapping(value = "updateTest",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateTest(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    String token = httpServletRequest.getHeader("token");
                    if(testDTO.getTeacherID() == Long.parseLong(JwtUtil.getUserId(token))){
                        testServices.updateTest(testDTO);
                        rsp.setRspData(testDTO);
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

    @ApiOperation(value = "填充考试（测试用）")
    @GetMapping("fillTest")
    public void fillTest(){
        testServices.fillTest();
    }

}