package com.book.snow.acl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.snow.model.user.GoogleUser;

import java.util.List;

public interface IGoogleService extends IService<GoogleUser> {
    Page<GoogleUser> selectUserPage(Page<GoogleUser> pageParam, GoogleUser googleUser);

    List<GoogleUser> selectByEmail(String email);
}
