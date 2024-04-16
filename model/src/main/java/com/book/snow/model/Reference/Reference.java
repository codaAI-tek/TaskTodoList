package com.book.snow.model.Reference;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Ref实体类")
@TableName("reference")
public class Reference extends BaseEntity {

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "内容")
    @TableField("context")
    private String context;

    @ApiModelProperty(value = "资源")
    @TableField("source")
    private String source;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

}
