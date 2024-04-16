package com.book.snow.acl.service.Impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.snow.acl.mapper.ReferenceMapper;
import com.book.snow.acl.service.IReferenceService;
import com.book.snow.model.Reference.Reference;
import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceImpl extends ServiceImpl<ReferenceMapper, Reference> implements IReferenceService {
    @Override
    public IPage<Reference> selectTaskPage(Page<Reference> pageParam, Reference reference) {

        LambdaQueryWrapper<Reference> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtil.isEmpty(reference.getContext())){
            wrapper.like(Reference::getContext,reference.getContext());
        }
        Page<Reference> referencePage = baseMapper.selectPage(pageParam, wrapper);
        return referencePage;
    }
}
