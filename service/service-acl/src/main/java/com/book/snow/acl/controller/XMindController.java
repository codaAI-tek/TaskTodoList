package com.book.snow.acl.controller;


import cn.hutool.json.JSONObject;
import com.book.snow.common.result.JsonResult;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.XMind.XMind;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import cn.hutool.json.JSONUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Api(tags = "算法端对接")
@RestController
@RequestMapping("/web/taskgpt")
public class XMindController {

    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("获取算法端信息(生成思维导图)")
    @PostMapping("/getInfo")
    public JsonResult getInfo(
            HttpServletRequest request,
            @RequestBody XMind xMind
            ){
//        String token = request.getHeader("Authorization");
//        Claims body = JwtUtuil.parseJwt(token);
        xMind.setToken("token");
        xMind.setUser(1L);

            try {
                // 设置URL
                URL url = new URL("http://localhost:5001/add_task");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 设置请求方法
                connection.setRequestMethod("POST");

                // 设置请求头
                connection.setRequestProperty("Content-Type", "application/json");

                // 启用输出
                connection.setDoOutput(true);

                // 设置请求体
                String jsonInputString = "{\"token\": "+xMind.getToken()+", \"user\": "+xMind.getUser()+", \"text\": "+xMind.getText()+", \"task\": "+xMind.getTask()+"}";
                try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                    wr.write(jsonInputString.getBytes());
                }

                // 读取响应
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.out.println(response.toString());
                    return JsonResult.ok(response);
                }
                // 关闭连接
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.fail(e);
            }
    }

    @ApiOperation("获取算法端信息(Test)")
    @PostMapping("/test")
    public Object getTest(@RequestBody XMind xMind){
        return xMind;
    }

}
