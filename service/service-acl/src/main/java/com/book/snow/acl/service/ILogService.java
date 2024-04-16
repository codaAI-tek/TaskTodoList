package com.book.snow.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.snow.model.Log.Log;

import java.util.List;

public interface ILogService extends IService<Log> {
    List<Log> selectByIp(String ip);

    List<Log> selectByPath(String path);
}
