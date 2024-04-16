package com.book.snow.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.snow.acl.service.IReferenceService;
import com.book.snow.acl.service.ITaskRefService;
import com.book.snow.common.result.JsonResult;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.Reference.Reference;
import com.book.snow.model.TaskRef.TaskRef;
import com.book.snow.model.base.BaseEntity;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Reference管理")
@RestController
@RequestMapping("/web/ref")
public class ReferenceController extends BaseEntity {

    @Autowired
    IReferenceService referenceService;

    @Autowired
    ITaskRefService taskRefService;

    @ApiOperation("分页查询Reference")
    @GetMapping("{page}/{limit}")
    public JsonResult pageList(
            @ApiParam(name = "page",value = "当前页数",required = true)
            @PathVariable long page,
            @ApiParam(name = "limit",value = "每页数量",required = true)
            @PathVariable long limit,
            @ApiParam(name = "Task",value = "查询对象")
            Reference reference
    ){
        Page<Reference> pageParam = new Page<>(page,limit);

        IPage<Reference> referenceIPage = referenceService.selectTaskPage(pageParam, reference);
        return JsonResult.ok(referenceIPage);
    }

    @ApiOperation("根据ID查询Reference")
    @GetMapping("/getInfo/{id}")
    public JsonResult getInfo(
            @ApiParam(name = "id",value = "用户ID",required = true)
            @PathVariable long id
    ){
        Reference reference = referenceService.getById(id);
        return JsonResult.ok(reference);
    }

    @ApiOperation("添加Reference")
    @PostMapping("/insert")
    public JsonResult insertUser(HttpServletRequest request, @RequestBody Reference reference){
        TaskRef taskRef = new TaskRef();

        String token = request.getHeader("Authorization");
        Claims body = JwtUtuil.parseJwt(token);
        reference.setUserId(Long.valueOf(body.get("userId").toString()));

        boolean isSuccess = referenceService.save(reference);
        if(isSuccess){
            taskRef.setRefId(reference.getId());
            boolean save = taskRefService.save(taskRef);
            if(save){
                return JsonResult.ok(null);
            }else {
                return JsonResult.fail(null);
            }

        }else {
            return JsonResult.fail(null);
        }
    }

    @ApiOperation("更新Reference")
    @PutMapping("/update")
    public JsonResult updateUser(@RequestBody Reference reference){
        boolean isSuccess = referenceService.updateById(reference);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }


    @ApiOperation("根据ID删除Reference")
    @DeleteMapping("/remove/{id}")
    public JsonResult removeById(
            @ApiParam(name = "id",value = "用户id",required = true)
            @PathVariable long id
    ){
        boolean isSuccess = referenceService.removeById(id);
        if(isSuccess){
            return JsonResult.ok(null);
        }else {
            return JsonResult.fail(null);
        }
    }

}
