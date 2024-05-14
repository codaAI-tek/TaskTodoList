package com.book.snow.acl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.snow.acl.service.ITaskRefService;
import com.book.snow.acl.service.ITaskService;
import com.book.snow.common.result.JsonResult;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.Task.Task;
import com.book.snow.model.TaskRef.TaskRef;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Task管理")
@RestController
@RequestMapping("/web/task")
public class TaskController {

    @Autowired
    ITaskService taskService;

    @Autowired
    ITaskRefService taskRefService;

    @ApiOperation("分页查询Task")
    @GetMapping("{page}/{limit}")
    public JsonResult pageList(
            @ApiParam(name = "page",value = "当前页数",required = true)
            @PathVariable long page,
            @ApiParam(name = "limit",value = "每页数量",required = true)
            @PathVariable long limit,
            @ApiParam(name = "Task",value = "查询对象")
            Task task
    ){
        Page<Task> pageParam = new Page<>(page,limit);

        IPage<Task> taskIPage = taskService.selectTaskPage(pageParam, task);
        return JsonResult.ok(taskIPage);
    }

    @ApiOperation("根据ID查询Task")
    @GetMapping("/getInfo/{id}")
    public JsonResult getInfo(
            @ApiParam(name = "id",value = "用户ID",required = true)
            @PathVariable long id
    ){
        Task task = taskService.selectById(id);
        return JsonResult.ok(task);
    }

    @ApiOperation("添加Task")
    @PostMapping("/insert")
    public JsonResult insertUser(HttpServletRequest request, @RequestBody Task task){
        TaskRef taskRef = new TaskRef();

        String token = request.getHeader("Authorization");
        Claims body = JwtUtuil.parseJwt(token);
        task.setUserId(Long.valueOf(body.get("userId").toString()));

        System.out.println(task.getUserId());
        boolean isSuccess = taskService.save(task);
        if(isSuccess){
            taskRef.setTaskId(task.getId());
            boolean save = taskRefService.save(taskRef);
            if(save){
                return JsonResult.ok(null);
            }else {
                return JsonResult.fail(null);
            }

        }else {
            return JsonResult.fail(null);
        }
//        return JsonResult.ok(null);
    }

    @ApiOperation("更新Task")
    @PutMapping("/update")
    public JsonResult updateUser(@RequestBody Task task){
        boolean isSuccess = taskService.updateById(task);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }


    @ApiOperation("根据ID删除Task")
    @DeleteMapping("/remove/{id}")
    public JsonResult removeById(
            @ApiParam(name = "id",value = "用户id",required = true)
            @PathVariable long id
    ){
        boolean isSuccess = taskService.removeById(id);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }

}
