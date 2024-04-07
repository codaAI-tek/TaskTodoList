package com.book.snow.common.exception;


import com.book.snow.common.result.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //AOP 面向切面
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult error(Exception e){
        e.printStackTrace();
        return JsonResult.fail(null);
    }

    //自定义异常处理
    @ExceptionHandler(SnowException.class)
    @ResponseBody
    public JsonResult error(SnowException e){
        e.printStackTrace();
        return JsonResult.fail(null);
    }
}