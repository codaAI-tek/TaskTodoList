package com.book.snow.acl.service.Impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.GoogleLoginMapper;
import com.book.snow.acl.service.IGoogleService;
import com.book.snow.model.user.GoogleUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleServiceImpl extends ServiceImpl<GoogleLoginMapper,GoogleUser> implements IGoogleService {
    @Override
    public Page<GoogleUser> selectUserPage(Page<GoogleUser> pageParam, GoogleUser googleUser) {

        LambdaQueryWrapper<GoogleUser> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtil.isEmpty(googleUser.getEmail())){
            queryWrapper.like(GoogleUser::getEmail,googleUser.getEmail());
        }
        Page<GoogleUser> googleUserPage = baseMapper.selectPage(pageParam, queryWrapper);

        return googleUserPage;
    }

    @Override
    public List<GoogleUser> selectByEmail(String email) {
        QueryWrapper<GoogleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        List<GoogleUser> list = baseMapper.selectList(wrapper);
        return list;
    }
}
