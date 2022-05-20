package com.example.examsys.Controller;


import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Services.LectureServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("lecture")
@ResponseBody
@Api(tags = "课程控制器")
public class LectureController {
    @Autowired
    private LectureServices lectureServices;

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "添加课程")
    @PostMapping(value = "addLecture", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addLecture(@RequestBody LectureDTO lectureDTO) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    lectureServices.addLecture(lectureDTO);
                    rsp.setRspData(lectureDTO);
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
    @ApiOperation(value = "id删除课程")
    @DeleteMapping("deleteLecture/{id}")
    public Callable<ResponseData> deleteLectureById(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    lectureServices.deleteLectureById(id);
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
    @ApiOperation(value = "id寻找课程")
    @GetMapping("findLecture/{id}")
    public Callable<ResponseData> findLectureById(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(new LectureDTO(lectureServices.findLectureById(id)));
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
    @ApiOperation(value = "修改课程信息")
    @PutMapping(value = "updateLecture", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateLecture(@RequestBody LectureDTO lectureDTO) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    lectureServices.updateLecture(lectureDTO);
                    rsp.setRspData(lectureDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "填充课程（测试用）")
    @GetMapping("fillLecture")
    public void fillLecture() {
        lectureServices.fillLecture();
    }

}