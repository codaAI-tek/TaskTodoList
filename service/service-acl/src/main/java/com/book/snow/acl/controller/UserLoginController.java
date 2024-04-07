package com.book.snow.acl.controller;


import com.book.snow.common.result.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin/acl/user")
public class UserLoginController {

    @PostMapping("/login")
    public JsonResult login(){
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return JsonResult.ok(map);
    }

    @GetMapping("/info")
    public JsonResult getInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","AKAYWJ");
        map.put("avatar","https://dingyue.ws.126.net/2021/0506/5d131e1bg00qsnuw1009tc000b4008cg.gif");
        return JsonResult.ok(map);
    }

    @PostMapping("/logout")
    public JsonResult logout(){
        return JsonResult.ok(null);
    }

}
