package com.cms.service;

import com.cms.base.service.IService;
import com.cms.entity.ClassInfo;
import com.cms.entity.Course;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.vo.StudentVO;

import java.util.List;

/**
 * Created by hs on 2019.5.14.
 */
public interface CourseService extends IService<Course>{

    void createCourse(Course course);
}
