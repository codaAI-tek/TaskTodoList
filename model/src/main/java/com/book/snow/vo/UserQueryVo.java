package com.book.snow.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryVo {

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String wxNum;


}
