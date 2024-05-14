package com.book.snow.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.snow.model.Reference.Reference;

import java.util.List;

public interface IReferenceService extends IService<Reference> {
    IPage<Reference> selectTaskPage(Page<Reference> pageParam, Reference reference);

    List<Reference> selectByTaskId(Long taskId);
}
