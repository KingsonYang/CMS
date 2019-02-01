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

    public List<CourseInfo> selectAll(CourseInfo courseInfo){
        return courseInfoMapper.selectAll(courseInfo);
    }
}
