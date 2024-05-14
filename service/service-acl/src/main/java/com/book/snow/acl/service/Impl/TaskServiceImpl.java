package com.book.snow.acl.service.Impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.TaskMapper;
import com.book.snow.acl.service.IReferenceService;
import com.book.snow.acl.service.ITaskRefService;
import com.book.snow.acl.service.ITaskService;
import com.book.snow.model.Reference.Reference;
import com.book.snow.model.Task.Task;
import com.book.snow.model.TaskRef.TaskRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    ITaskRefService taskRefService;

    @Autowired
    IReferenceService referenceService;

    @Override
    public IPage<Task> selectTaskPage(Page<Task> pageParam, Task task) {
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtil.isEmpty(task.getPriority())){
            wrapper.like(Task::getPriority,task.getPriority());
        }
        Page<Task> taskPage = baseMapper.selectPage(pageParam, wrapper);

        return taskPage;
    }

    @Override
    public Task selectById(long id) {
        Task task = baseMapper.selectById(id);

        LambdaQueryWrapper<TaskRef> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TaskRef::getTaskId,id);

        List<TaskRef> list = taskRefService.list(wrapper);
        List<Long> list1 = list.stream().map(item -> item.getRefId()).collect(Collectors.toList());
        List<Reference> referenceList = referenceService.listByIds(list1);
        task.setRefList(referenceList);

        return task;
    }
}
