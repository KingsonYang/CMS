package com.cms.dao;

import com.cms.entity.ClassroomInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassroomInfoMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(ClassroomInfo record);
    ClassroomInfo selectByPrimaryKey(Integer id);
    List<ClassroomInfo> selectAll();
    int updateByPrimaryKey(ClassroomInfo record);
}