package com.cms.service.impl;

import com.cms.base.service.impl.BaseService;
import com.cms.dao.ClassInfoMapper;
import com.cms.dao.UserMapper;
import com.cms.entity.ClassInfo;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.StudentService;
import com.cms.service.UserService;
import com.cms.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Service
public class StudentServiceImpl extends BaseService<Student> implements StudentService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;



    @Override
    public List<StudentVO> queryList(List<Student> students){
        List<StudentVO> studentVOS = new ArrayList<>();
        for( Student student :students){
            StudentVO studentVO = new StudentVO();
            studentVO.setUser(userMapper.selectByPrimaryKey(student.getStuId()));
            studentVO.setStuname(studentVO.getUser().getUsername());
            studentVO.setClassInfo(classInfoMapper.selectByPrimaryKey(student.getClassId()));
            studentVO.setClassname(studentVO.getClassInfo().getClassShortname());
            studentVOS.add(studentVO);
        }

        return studentVOS;
    }


    @Override
    public User queryUser(Integer stuId) {

        return userMapper.selectByPrimaryKey(stuId);
    }

    @Override
    public ClassInfo queryClass(Integer classId) {
        return classInfoMapper.selectByPrimaryKey(classId);
    }
}
