package com.book.snow.acl.service.Impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.TaskMapper;
import com.book.snow.acl.service.ITaskService;
import com.book.snow.model.Task.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {


    @Override
    public IPage<Task> selectTaskPage(Page<Task> pageParam, Task task) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtil.isEmpty(task.getPriority())){
            wrapper.like(Task::getPriority,task.getPriority());
        }
        Page<Task> taskPage = baseMapper.selectPage(pageParam, wrapper);

        return taskPage;
    }
}
