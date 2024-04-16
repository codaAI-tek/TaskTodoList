package com.book.snow.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.snow.model.Task.Task;
import com.book.snow.model.user.GoogleUser;

public interface ITaskService extends IService<Task> {
    IPage<Task> selectTaskPage(Page<Task> pageParam, Task task);
}
