package com.book.snow.common.result;


import lombok.Data;

@Data
public class JsonResult<T> {


    //状态码
    private Integer code;
    //信息
    private String message;
    //数据
    private T data;

    //构造私有化
    private JsonResult() { }

    //设置数据,返回对象的方法
    public static<T> JsonResult<T> build(T data,ResultCodeEnum resultCodeEnum) {
        //创建Resullt对象，设置值，返回对象
        JsonResult<T> result = new JsonResult<>();
        //判断返回结果中是否需要数据
        if(data != null) {
            //设置数据到result对象
            result.setData(data);
        }
        //设置其他值
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        //返回设置值之后的对象
        return result;
    }

    //成功的方法
    public static<T> JsonResult<T> ok(T data) {
        JsonResult<T> result = build(data, ResultCodeEnum.SUCCESS);
        return result;
    }

    //失败的方法
    public static<T> JsonResult<T> fail(T data) {
        return build(data,ResultCodeEnum.FAIL);
    }

    public static<T> JsonResult<T> requestFail(T data){
        return build(data,ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
    }

}
