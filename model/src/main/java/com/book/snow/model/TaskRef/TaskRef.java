package com.book.snow.model.TaskRef;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("TaskRef实体类")
@TableName("task_ref")
public class TaskRef extends BaseEntity {

    @ApiModelProperty(value = "Task_id")
    @TableField("task_id")
    private Long taskId;

    @ApiModelProperty(value = "Ref_id")
    @TableField("ref_id")
    private Long refId;

}
