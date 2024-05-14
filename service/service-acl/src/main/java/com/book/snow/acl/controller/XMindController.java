package com.book.snow.acl.controller;


import com.book.snow.common.result.JsonResult;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.XMind.XMind;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.*;
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
        String token = request.getHeader("Authorization");
        if(token == null){
            xMind.setToken("fighting");
            xMind.setUser(xMind.getUser());
        }else{
            Claims body = JwtUtuil.parseJwt(token);
            xMind.setToken("fighting");
            xMind.setUser(""+body.getId());
        }

        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construct request body
        String requestBody = "{\"token\": \"fighting\", \"user\": \"winger\", \"text\": \"test text\", \"task\": \"test task\"}" ;
        String requestBody2 = "{\"token\": \"fighting\", \"user\": \""+xMind.getUser()+"\", \"text\": \""+xMind.getText()+"\", \"task\": \""+xMind.getTask()+"\"}" ;
        System.out.println(requestBody);
        System.out.println(requestBody2);


        // Create HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody2, headers);

        // Define the URL
        String url = "http://localhost:5001/gen_mind";

        // Send POST request with exchange method
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Get response body
        String responseBody = responseEntity.getBody();
        System.out.println("Response: " + responseBody);
        return JsonResult.ok(responseBody);
    }

//    @ApiOperation("算法端添加Task")
//    @PostMapping("/addTask")
//    public JsonResult addTask(){
//
//    }

    @ApiOperation("获取算法端信息(Test)")
    @PostMapping("/test")
    public Object getTest(@RequestBody XMind xMind){
        XMind xMind1 = new XMind();
        xMind1.setToken("hhh");
        xMind1.setText("hhhh");


        RestTemplate restTemplate = new RestTemplate();

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construct request body
        String requestBody = "{\"token\":\"test token\",\"text\":\"text\",\"task\":\"1\",\"user\":\"1\"}";
        String requestBody2 = "{\"token\": \"fighting\", \"user\": \""+xMind.getUser()+"\", \"text\": \""+xMind.getText()+"\", \"task\": \""+xMind.getTask()+"\"}" ;
        System.out.println(requestBody);
        System.out.println(requestBody2);

//        // Create HttpEntity with headers and body
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // Define the URL
//        String url = "http://localhost:5001/gen_mind";
//
//        // Send POST request with exchange method
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                String.class
//        );
//
//        // Get response body
//        String responseBody = responseEntity.getBody();
//        System.out.println("Response: " + responseBody);
//        return JsonResult.ok(responseBody);
        return JsonResult.ok(null);
    }

}
