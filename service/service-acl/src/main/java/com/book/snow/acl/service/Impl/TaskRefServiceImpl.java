package com.book.snow.acl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.TaskRefMapper;
import com.book.snow.acl.service.ITaskRefService;
import com.book.snow.model.TaskRef.TaskRef;
import org.springframework.stereotype.Service;

@Service
public class TaskRefServiceImpl extends ServiceImpl<TaskRefMapper, TaskRef> implements ITaskRefService {
}
