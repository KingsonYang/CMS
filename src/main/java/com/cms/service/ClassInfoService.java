package com.cms.service;

import com.cms.dao.ClassInfoMapper;
import com.cms.entity.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    public int insert(ClassInfo classroom){
        return classInfoMapper.insert(classroom);
    }

    public int deleteByPrimaryKey(int id){
        return classInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimartKey(ClassInfo classroom){
        return classInfoMapper.updateByPrimaryKey(classroom);
    }

    public List<ClassInfo> selectAll(){
        return classInfoMapper.selectAll();
    }

}

