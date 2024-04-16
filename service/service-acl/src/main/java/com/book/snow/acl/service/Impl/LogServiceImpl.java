package com.book.snow.acl.service.Impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.LogMapper;
import com.book.snow.acl.service.ILogService;
import com.book.snow.model.Log.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    @Override
    public List<Log> selectByIp(String ip) {
        if(StringUtil.isEmpty(ip)){
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("ip",ip);

        List list = baseMapper.selectList(wrapper);

        return list;
    }

    @Override
    public List<Log> selectByPath(String path) {
        if(StringUtil.isEmpty(path)){
            return null;
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("path",path);

        List list = baseMapper.selectList(wrapper);

        return list;
    }
}
