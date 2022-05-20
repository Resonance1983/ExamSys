package com.example.examsys.Controller;


import com.example.examsys.DTO.QuestionDTO;
import com.example.examsys.Entity.Question;
import com.example.examsys.Services.QuestionsServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("question")
@ResponseBody
@Api(tags = "问题管理控制器")
public class QuestionController {
    @Autowired
    private QuestionsServices questionServices;

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "添加问题")
    @PostMapping(value="addQuestion",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> addQuestion(@RequestBody QuestionDTO questionDTO){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    questionServices.addQuestion(questionDTO);
                    rsp.setRspData(questionDTO);
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
    @ApiOperation(value = "id删除问题")
    @DeleteMapping("deleteQuestion/{id}")
    public Callable<ResponseData> deleteQuestionById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    questionServices.deleteQuestionById(id);
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

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "id寻找问题")
    @GetMapping("findQuestion/{id}")
    public Callable<ResponseData> findQuestionById(@PathVariable("id") Long id){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    rsp.setRspData(new QuestionDTO(questionServices.findQuestionById(id)));
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
    @ApiOperation(value = "批量id寻找问题")
    @GetMapping("findQuestions/{id}")
    public Callable<ResponseData> findQuestionsById(@PathVariable("id") ArrayList<Long> ids){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try{
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    ArrayList<Question> questions = new ArrayList<>();
                    ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
                    questions = questionServices.findQuestionsById(ids);
                    for(Question question:questions){
                        questionDTOS.add(new QuestionDTO(question));
                    }
                    rsp.setRspData(questionDTOS);
                    return rsp;
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
    @ApiOperation(value = "修改问题信息")
    @PutMapping(value = "updateQuestion",produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateQuestion(@RequestBody QuestionDTO questionDTO){
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName()+"，执行服务:"+Thread.currentThread().getStackTrace()[1].getMethodName());
                    questionServices.updateQuestion(questionDTO);
                    rsp.setRspData(questionDTO);
                }catch (Exception e){
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @ApiOperation(value = "填充问题（测试用）")
    @GetMapping("fillQuestion")
    public void fillQuestion(){
        questionServices.fillQuestion();
    }

}