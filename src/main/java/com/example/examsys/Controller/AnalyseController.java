package com.example.examsys.Controller;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Services.AnalyseServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("analyse")
@ResponseBody
@Api(tags = "分析管理控制器")
public class AnalyseController {
    @Autowired
    private AnalyseServices analyseServices;

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "单个问题的统计")
    @GetMapping("questionState/{testid}/{questionid}")
    public Callable<ResponseData> questionState(@PathVariable("testid") Long testid,@PathVariable("questionid") Long questionid){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(analyseServices.questionState(testid,questionid));
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
    @ApiOperation(value = "单个类型的统计")
    @GetMapping("typeState/{testid}/{type}")
    public Callable<ResponseData> typeState(@PathVariable("testid") Long testid,@PathVariable("questionid") String type){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(analyseServices.typeState(testid,type));
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
    @ApiOperation(value = "多个指定问题的统计")
    @GetMapping("questionsState")
    public Callable<ResponseData> questionsState(@RequestParam Long testID,@RequestBody ArrayList<Long> questionsID){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(analyseServices.questionsState(testID,questionsID));
                }catch (Exception e){
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

}
