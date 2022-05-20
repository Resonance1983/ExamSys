package com.example.examsys.Controller;

import com.example.examsys.DTO.AnswerSheetDTO;
import com.example.examsys.Services.AnswerSheetServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.MyTool;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("answersheet")
@ResponseBody
@Api(tags = "答卷管理控制器")
public class AnswerSheetController {
    @Autowired
    private AnswerSheetServices answerSheetServices;

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "上传答卷")
    @PostMapping(value = "addAnswerSheet", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addAnswerSheet(@RequestBody AnswerSheetDTO answerSheetDTO, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    String token = httpServletRequest.getHeader("token");
                    if ((JwtUtil.checkSign(token, 2) || answerSheetDTO.getStudentID() == Long.parseLong(JwtUtil.getUserId(token)))
                            && MyTool.inDuration(new Date(), answerSheetServices.getTestDuration(answerSheetDTO))) {
                        answerSheetServices.addAnswerSheet(answerSheetDTO);
                        rsp.setRspData(answerSheetDTO);
                    } else {
                        rsp.setFailed();
                        rsp.setRspMsg("权限不足");
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

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "id删除答卷")
    @DeleteMapping("deleteAnswerSheet/{id}")
    public Callable<ResponseData> deleteAnswerSheetByID(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    answerSheetServices.deleteAnswerSheetById(id);
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
    @ApiOperation(value = "id寻找答卷")
    @GetMapping("findAnswerSheet/{id}")
    public Callable<ResponseData> findAnswerSheetById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    String token = httpServletRequest.getHeader("token");
                    if (JwtUtil.checkSign(token, 2) || id == Long.parseLong(JwtUtil.getUserId(token))) {
                        rsp.setRspData(new AnswerSheetDTO(answerSheetServices.findAnswerSheetById(id)));
                    } else {
                        rsp.setFailed();
                        rsp.setRspMsg("权限不足");
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
    @ApiOperation(value = "更改答卷")
    @PutMapping(value = "updateAnswerSheet", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateAnswerSheet(@RequestBody AnswerSheetDTO answerSheetDTO, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + Thread.currentThread().getStackTrace()[1].getMethodName());
                    String token = httpServletRequest.getHeader("token");
                    if ((JwtUtil.checkSign(token, 2) || answerSheetDTO.getStudentID() == Long.parseLong(JwtUtil.getUserId(token)))
                            && MyTool.inDuration(new Date(), answerSheetServices.getTestDuration(answerSheetDTO))) {
                        answerSheetServices.updateAnswerSheet(answerSheetDTO);
                        rsp.setRspData(answerSheetDTO);
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

    @ApiOperation(value = "填充答卷（测试用）")
    @GetMapping("fillAnswersheet")
    public void fillTeacher() {
        answerSheetServices.fillAnswerSheet();
    }

}
