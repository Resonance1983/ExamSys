package com.example.examsys.Controller;


import com.example.examsys.DTO.QuestionsDTO;
import com.example.examsys.Entity.Question;
import com.example.examsys.Services.QuestionsServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
@ResponseBody
public class QuestionController {
    @Autowired
    private QuestionsServices questionServices;

    @JwtToken(requirePower = 2)
    @PostMapping(value="addQuestion",produces = "application/json;charset=UTF-8")
    public ResponseData addQuestion(@RequestBody QuestionsDTO questionDTO){
        ResponseData rsp = new ResponseData();
        try{
            questionServices.addQuestion(questionDTO);
            rsp.setRspData(questionDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @DeleteMapping("deleteQuestion/{id}")
    public ResponseData deleteQuestionById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            questionServices.deleteQuestionById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @GetMapping("findQuestion/{id}")
    public ResponseData findQuestionById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Question question = questionServices.findQuestionById(id);
            rsp.setRspData(question);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @PutMapping(value = "updateQuestion",produces = "application/json;charset=UTF-8")
    public ResponseData updateQuestion(@RequestBody QuestionsDTO questionDTO){
        ResponseData rsp = new ResponseData();
        try {
            questionServices.updateQuestion(questionDTO);
            rsp.setRspData(questionDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @GetMapping("fillQuestion")
    public void fillQuestion(){
        questionServices.fillQuestion();
    }

}