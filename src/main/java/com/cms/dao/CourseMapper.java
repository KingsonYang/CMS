package com.cms.dao;

import com.cms.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);
}