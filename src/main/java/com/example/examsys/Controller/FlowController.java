package com.example.examsys.Controller;

import com.example.examsys.Services.FlowServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("flow")
@ResponseBody
@Api(tags = "人员流动控制器")
public class FlowController {
    @Autowired
    private FlowServices flowServices;

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "学生报名课程")
    @PostMapping(value = "lectureAddStudent/{lectureID}/{studentID}", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> lectureAddStudent(@PathVariable("lectureID") long lectureID, @PathVariable("studentID") long studentID) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "lectureAddStudent");
                    flowServices.lectureAddStudent(lectureID, studentID);
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
    @ApiOperation(value = "学生报名课程")
    @PostMapping(value = "teacherHoldLecture/{teacherID}/{lectureID}", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> teacherHoldLecture(@PathVariable("teacherID") long teacherID, @PathVariable("lectureID") long lectureID) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "lectureAddStudent");
                    flowServices.teacherHoldLecture(teacherID, lectureID);
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
}
