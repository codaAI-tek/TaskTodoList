package com.book.snow.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.snow.model.Task.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMapper extends BaseMapper<Task> {
}
