package com.book.snow.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.UserBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("User")
@TableName("user")
public class User extends UserBaseEntity {

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "微信号")
    @TableField("wx_num")
    private String wxNum;

    @ApiModelProperty(value = "是否为会员（1：是   0：否")
    @TableField("is_vip")
    private String idVip;

    @ApiModelProperty(value = "是否为新用户（1：是  0：否")
    @TableField("is_new")
    private String isNew;

    @ApiModelProperty(value = "小程序open id")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "微信开放平台unionID")
    @TableField("union_id")
    private String unionId;

}
