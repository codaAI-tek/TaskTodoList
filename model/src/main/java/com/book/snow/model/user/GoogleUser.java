package com.book.snow.model.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.book.snow.model.base.UserBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("googleLogin(谷歌登录)")
@TableName("user")
public class GoogleUser extends UserBaseEntity {

    @ApiModelProperty(value = "账号")
    @TableField("user_account")
    private String userAccount;

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

    @ApiModelProperty(value = "地区")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "令牌")
    @TableField(exist = false)
    private String token;


}
