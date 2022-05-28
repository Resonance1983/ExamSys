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
import java.util.ArrayList;
import java.util.HashMap;
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
    @PostMapping(value = "addTest", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addTest(@RequestBody TestDTO testDTO) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "addTest");
                    rsp.setRspData(new TestDTO(testServices.addTest(testDTO)));
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
    @ApiOperation(value = "智能组卷发布考试")
    @PostMapping(value = "addTestAutomatic", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addTestAutomatic(@RequestBody TestDTO testDTO, @RequestParam String subject, @RequestParam HashMap<String, Integer> typeNumberMap) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "addTestAutomatic");
                    testServices.addTestAutomatic(testDTO, subject, typeNumberMap);
                    rsp.setRspData(testDTO);
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
    @ApiOperation(value = "id删除考试")
    @DeleteMapping("deleteTest/{id}")
    public Callable<ResponseData> deleteTestById(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "deleteTestById");
                    testServices.deleteTestById(id);
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
    @ApiOperation(value = "id寻找考试，除了管理员和出卷老师都不能获得问卷信息")
    @GetMapping("findTest/{id}")
    public Callable<ResponseData> findTestById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "findTestById");
                    String token = httpServletRequest.getHeader("token");
                    Test test = testServices.findTestById(id);
                    //不是管理员或者不是出卷老师就不能得到问卷id组
                    if (JwtUtil.getPower(token) < 3 || !(test.getTeacherID() == Long.parseLong(JwtUtil.getUserId(token))))
                        test.setQuestionsID(null);
                    rsp.setRspData(new TestDTO(test));
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
    @ApiOperation(value = "修改考试信息,只有出卷老师和管理员才能修改")
    @PutMapping(value = "updateTest", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateTest(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "updateTest");
                    String token = httpServletRequest.getHeader("token");
                    //是出卷老师或者管理员就允许修改
                    if (JwtUtil.getPower(token) > 2 || (testDTO.getTeacherID() == Long.parseLong(JwtUtil.getUserId(token)))) {
                        testServices.updateTest(testDTO);
                        rsp.setRspData(testDTO);
                    } else {
                        rsp.setFailed();
                        rsp.setRspMsg("非修改用户");
                    }
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
    @ApiOperation(value = "寻找所有考试")
    @GetMapping("findAllTest")
    public Callable<ResponseData> findAllTest() {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "findAllTest");
                    ArrayList<TestDTO> testDTOS = new ArrayList<>();
                    ArrayList<Test> tests = testServices.findAllTests();
                    for (Test test : tests) {
                        testDTOS.add(new TestDTO(test));
                    }
                    rsp.setRspData(testDTOS);
                } catch (Exception e) {
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
    public void fillTest() {
        testServices.fillTest();
    }

}