package com.example.examsys.Controller;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Entity.Admin;
import com.example.examsys.Services.AdminServices;
import com.example.examsys.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@ResponseBody
public class AdminController {
    @Autowired
    private AdminServices adminServices;

    @GetMapping("findAdmin/{id}")
    public ResponseData findAdminByID(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try {
            Admin admin = adminServices.findAdminByID(id);
            rsp.setRspData(admin);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @PutMapping(value = "updateAdmin",produces = "application/json;charset=UTF-8")
    public ResponseData updateAdmin(@RequestBody AdminDTO adminDTO){
        ResponseData rsp = new ResponseData();
        try {
            adminServices.updateAdmin(adminDTO);
            rsp.setRspData(adminDTO);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setFailed();
            rsp.setRspMsg(e.toString());
        }
        return rsp;
    }

    @PostMapping("fillAdmin")
    public void fillAdmin(){
        adminServices.fillAdmin();
    }

}
