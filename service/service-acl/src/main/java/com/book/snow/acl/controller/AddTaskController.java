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

import javax.servlet.http.HttpServletRequest;

@Api(tags = "算法端")
@RestController
@RequestMapping("/web/addTask")
public class AddTaskController {

    @ApiOperation("获取算法端信息(生成思维导图)")
    @PostMapping("/add")
    public JsonResult addTask(
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
        String url = "http://localhost:5001/add_task";

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

}
