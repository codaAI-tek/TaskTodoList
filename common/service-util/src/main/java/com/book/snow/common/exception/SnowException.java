package com.book.snow.common.exception;

import com.book.snow.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class SnowException extends RuntimeException{

    private Integer code;

    public SnowException(String message,Integer code){
        super(message);
        this.code = code;
    }

    public SnowException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
