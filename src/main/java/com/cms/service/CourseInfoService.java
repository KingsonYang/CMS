package com.cms.service;

import com.cms.dao.CourseInfoMapper;
import com.cms.entity.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    public int insert(CourseInfo courseInfo){
        return courseInfoMapper.insert(courseInfo);
    }

    public int deleteByPrimaryKey(int id){
        return courseInfoMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimartKey(CourseInfo courseInfo){
        return courseInfoMapper.updateByPrimaryKey(courseInfo);
    }

    public List<CourseInfo> selectAll(CourseInfo courseInfo){
        return courseInfoMapper.selectAll(courseInfo);
    }
}
