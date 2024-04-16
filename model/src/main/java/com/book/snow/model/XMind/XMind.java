package com.book.snow.model.XMind;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("XMind实体类")
public class XMind {

    @ApiModelProperty(value = "令牌",required = true)
    private String token;

    @ApiModelProperty(value = "用户ID",required = false)
    private Long user;

    @ApiModelProperty(value = "文本",required = false)
    private String text;

    @ApiModelProperty(value = "Task ID",required = false)
    private Long task;

}
