package com.cms.dao;

import com.cms.entity.ClassInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_info
     *
     * @mbg.generated
     */
    int insert(ClassInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_info
     *
     * @mbg.generated
     */
    ClassInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_info
     *
     * @mbg.generated
     */
    List<ClassInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table class_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ClassInfo record);
}