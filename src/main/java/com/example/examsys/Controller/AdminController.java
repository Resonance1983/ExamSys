package com.example.examsys.Controller;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Services.AdminServices;
import com.example.examsys.Support.JWT.JwtToken;
import com.example.examsys.Support.JWT.JwtUtil;
import com.example.examsys.Support.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("admin")
@ResponseBody
@Api(tags = "管理员控制器")
public class AdminController {
    @Autowired
    private AdminServices adminServices;

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "id寻找管理员")
    @GetMapping("findAdmin/{id}")
    public Callable<ResponseData> findAdminByID(@PathVariable("id") Long id) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "findAdminByID");
                    rsp.setRspData(new AdminDTO(adminServices.findAdminByID(id)));
                } catch (Exception e) {
                    e.printStackTrace();
                    rsp.setFailed();
                    rsp.setRspMsg(e.toString());
                }
                return rsp;
            }
        };
    }

    @JwtToken(requirePower = 3)
    @ApiOperation(value = "更新信息")
    @PutMapping(value = "updateAdmin", produces = "application/json;charset=UTF-8")
    public Callable<ResponseData> updateAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest httpServletRequest) {
        return new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                ResponseData rsp = new ResponseData();
                try {
                    System.out.println("异步执行线程:" + Thread.currentThread().getName() + "，执行服务:" + "updateAdmin");
                    String token = httpServletRequest.getHeader("token");
                    if (adminDTO.getId() == Long.parseLong(JwtUtil.getUserId(token))) {
                        adminServices.updateAdmin(adminDTO);
                        rsp.setRspData(adminDTO);
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

    @ApiOperation(value = "填充管理员（测试用）")
    @PostMapping("fillAdmin")
    public void fillAdmin() {
        adminServices.fillAdmin();
    }

}
