package com.book.snow.model.Task;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel("Task实体类")
@TableName("task")
public class Task extends BaseEntity {

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private long userId;

    @ApiModelProperty(value = "description",required = true)
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "priority(low,medium,high)",required = true)
    @TableField("priority")
    private String priority;

    @ApiModelProperty(value = "deadline")
    @TableField("deadline")
    private Timestamp deadline;

}
