package com.cms.service;

import com.cms.base.service.IService;
import com.cms.entity.ClassInfo;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.vo.StudentVO;

import java.util.List;
import java.util.Set;

/**
 * Created by hs on 2019.5.14.
 */
public interface StudentService extends IService<Student>{



    List<StudentVO> queryList(List<Student> students);

    /**
     * 根据学生编号获取学生信息
     * @param stuId
     * @return
     */
    User queryUser(Integer stuId);

    /**
     * 根据班级编号得到班级信息
     * @param classId
     * @return
     */
    ClassInfo queryClass(Integer classId);
}
