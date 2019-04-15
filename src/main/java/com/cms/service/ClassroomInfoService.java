package com.cms.service;

import com.cms.dao.ClassroomInfoMapper;
import com.cms.entity.ClassroomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomInfoService {

    @Autowired
    private ClassroomInfoMapper classroomInfoMapper;

    public int insert(ClassroomInfo courseInfo){
        return classroomInfoMapper.insert(courseInfo);
    }

    public int deleteByPrimaryKey(int id){
        return classroomInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimartKey(ClassroomInfo courseInfo){
        return classroomInfoMapper.updateByPrimaryKey(courseInfo);
    }

    public List<ClassroomInfo> selectAll(){
        return classroomInfoMapper.selectAll();
    }


}
