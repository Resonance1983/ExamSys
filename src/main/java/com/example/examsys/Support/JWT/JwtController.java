package com.example.examsys.Support.JWT;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/jwt")
@Api(tags = "JWT 测试接口")
public class JwtController {

    @PostMapping("/login")
    @ApiOperation(value = "登录并获取token", httpMethod = "POST")
    public Object login( String userName, String passWord){
        JSONObject jsonObject=new JSONObject();
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String userId = UUID.randomUUID().toString();
        // 生成签名
        String token= JwtUtil.sign(userId);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        userInfo.put("passWord", passWord);
        jsonObject.put("token", token);
        jsonObject.put("user", userInfo);
        return jsonObject;
    }

    @JwtToken
    @GetMapping("/getMessage")
    @ApiOperation(value = "该接口需要带签名才能访问")
    public String getMessage(){
        return "你已通过验证";
    }
}