package com.book.snow.model.Log;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Log")
@TableName("log")
public class Log extends BaseEntity {

    @ApiModelProperty(value = "IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "Host")
    @TableField("host")
    private String host;

    @ApiModelProperty(value = "url")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "port")
    @TableField("port")
    private String port;

    @ApiModelProperty(value = "path")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "count")
    @TableField("count")
    private Integer count;

}
