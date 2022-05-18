package com.example.examsys.Controller;


import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;
import com.example.examsys.Services.LectureServices;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lecture")
@ResponseBody
public class LectureController {
    @Autowired
    private LectureServices lectureServices;

    @PostMapping(value="addLecture",produces = "application/json;charset=UTF-8")
    public ResponseData addLecture(@RequestBody LectureDTO lectureDTO){
        ResponseData rsp = new ResponseData();
        try{
            lectureServices.addLecture(lectureDTO);
            rsp.setRspData(lectureDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @DeleteMapping("deleteLecture/{id}")
    public ResponseData deleteLectureById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            lectureServices.deleteLectureById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @GetMapping("findLecture/{id}")
    public ResponseData findLectureById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Lecture student = lectureServices.findLectureById(id);
            rsp.setRspData(student);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @PutMapping(value = "updateLecture",produces = "application/json;charset=UTF-8")
    public ResponseData updateLecture(@RequestBody LectureDTO lectureDTO){
        ResponseData rsp = new ResponseData();
        try {
            lectureServices.updateLecture(lectureDTO);
            rsp.setRspData(lectureDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @GetMapping("fillLecture")
    public void fillLecture(){
        lectureServices.fillLecture();
    }

}