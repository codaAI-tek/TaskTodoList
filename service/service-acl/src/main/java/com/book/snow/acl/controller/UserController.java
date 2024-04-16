package com.book.snow.acl.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.snow.acl.service.IGoogleService;
import com.book.snow.common.result.JsonResult;
import com.book.snow.model.user.GoogleUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/user/control")
public class UserController {
    @Autowired
    IGoogleService googleService;

    @ApiOperation("分页查询用户")
    @GetMapping("{page}/{limit}")
    public JsonResult pageList(
            @ApiParam(name = "page",value = "当前页数",required = true)
            @PathVariable long page,
            @ApiParam(name = "limit",value = "每页数量",required = true)
            @PathVariable long limit,
            @ApiParam(name = "GoogleUser",value = "查询对象")
            GoogleUser googleUser
    ){
        Page<GoogleUser> pageParam = new Page<>(page,limit);

        Page<GoogleUser> googleUserPage = googleService.selectUserPage(pageParam, googleUser);
        return JsonResult.ok(googleUserPage);
    }

    @ApiOperation("根据ID查询用户")
    @GetMapping("/getInfo/{id}")
    public JsonResult getInfo(
            @ApiParam(name = "id",value = "用户ID",required = true)
            @PathVariable long id
    ){
        GoogleUser googleUser = googleService.getById(id);
        return JsonResult.ok(googleUser);
    }

    @ApiOperation("添加用户")
    @PostMapping("/insert")
    public JsonResult insertUser(@RequestBody GoogleUser googleUser){
        boolean isSuccess = googleService.save(googleUser);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public JsonResult updateUser(@RequestBody GoogleUser googleUser){
        boolean isSuccess = googleService.updateById(googleUser);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }


    @ApiOperation("根据ID删除用户")
    @DeleteMapping("/remove/{id}")
    public JsonResult removeById(
            @ApiParam(name = "id",value = "用户id",required = true)
            @PathVariable long id
    ){
        boolean isSuccess = googleService.removeById(id);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }

}
