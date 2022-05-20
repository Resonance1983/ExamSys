package com.example.examsys.Support.JWT;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/jwt")
@Api(tags = "Jwt测试器")
public class JwtController {

    @PostMapping("/login")
    public Object login(String userName, String passWord) {
        JSONObject jsonObject = new JSONObject();
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String userId = UUID.randomUUID().toString();
        // 生成签名
        String token = JwtUtil.sign(userId, "user");
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        userInfo.put("passWord", passWord);
        jsonObject.put("token", token);
        jsonObject.put("user", userInfo);
        System.out.println("login");
        return jsonObject;
    }

    @JwtToken
    @GetMapping("/getMessage")
    @ApiOperation(value = "该接口需要带签名才能访问")
    public String getMessage() {
        return "你已通过验证";
    }
}