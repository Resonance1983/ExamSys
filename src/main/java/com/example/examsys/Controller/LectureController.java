package com.example.examsys.Controller;


import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;
import com.example.examsys.Services.LectureServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lecture")
@ResponseBody
@Api(tags = "课程控制器")
public class LectureController {
    @Autowired
    private LectureServices lectureServices;

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "添加课程")
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

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "id删除课程")
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

    @JwtToken(requirePower = 1)
    @ApiOperation(value = "id寻找课程")
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

    @JwtToken(requirePower = 2)
    @ApiOperation(value = "修改课程信息")
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

    @ApiOperation(value = "填充课程（测试用）")
    @GetMapping("fillLecture")
    public void fillLecture(){
        lectureServices.fillLecture();
    }

}