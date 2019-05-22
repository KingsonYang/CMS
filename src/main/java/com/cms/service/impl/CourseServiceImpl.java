package com.cms.service.impl;

import com.cms.base.service.impl.BaseService;
import com.cms.dao.ClassInfoMapper;
import com.cms.dao.UserMapper;
import com.cms.entity.ClassInfo;
import com.cms.entity.Course;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.CourseService;
import com.cms.service.StudentService;
import com.cms.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Service
public class CourseServiceImpl extends BaseService<Course> implements CourseService{

    @Override
    public void createCourse(Course course) {
        course.getBeginDate();
        course.getEndDate();
    }
}
