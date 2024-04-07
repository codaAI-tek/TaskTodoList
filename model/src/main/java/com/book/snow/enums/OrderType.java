package com.book.snow.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderType {

    UPORDER(1,"待支付"),
    WAITING_DELEVER(2,"代发货"),
    WAITING_TAKE(3,"待提货"),
    WAITING_COMMON(4,"待评论"),
    FINISHED(5,"已完成"),
    CANCEL(-1,"已取消");

    @EnumValue
    private Integer code;
    private String comment;

    private OrderType(Integer code,String comment){
        this.code = code;
        this.comment = comment;
    }
}
