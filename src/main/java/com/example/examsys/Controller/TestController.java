package com.example.examsys.Controller;


import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;
import com.example.examsys.Services.TestServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@ResponseBody
public class TestController {
    @Autowired
    private TestServices testServices;

    @JwtToken(requirePower = 2)
    @PostMapping(value="addTest",produces = "application/json;charset=UTF-8")
    public ResponseData addTest(@RequestBody TestDTO testDTO){
        ResponseData rsp = new ResponseData();
        try{
            testServices.addTest(testDTO);
            rsp.setRspData(testDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @DeleteMapping("deleteTest/{id}")
    public ResponseData deleteTestById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            testServices.deleteTestById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 1)
    @GetMapping("findTest/{id}")
    public ResponseData findTestById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Test question = testServices.findTestById(id);
            rsp.setRspData(question);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @PutMapping(value = "updateTest",produces = "application/json;charset=UTF-8")
    public ResponseData updateTest(@RequestBody TestDTO testDTO){
        ResponseData rsp = new ResponseData();
        try {
            testServices.updateTest(testDTO);
            rsp.setRspData(testDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @JwtToken(requirePower = 2)
    @GetMapping("fillTest")
    public void fillTest(){
        testServices.fillTest();
    }

}