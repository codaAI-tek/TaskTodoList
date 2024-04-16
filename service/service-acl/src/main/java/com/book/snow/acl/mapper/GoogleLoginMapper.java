package com.book.snow.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.snow.model.user.GoogleUser;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleLoginMapper extends BaseMapper<GoogleUser> {
}
