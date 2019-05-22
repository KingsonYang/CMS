package com.cms.service.impl;

import com.cms.base.service.impl.BaseService;
import com.cms.dao.ClassInfoMapper;
import com.cms.dao.CourseInfoMapper;
import com.cms.dao.UserMapper;
import com.cms.entity.ClassInfo;
import com.cms.entity.Score;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.ScoreService;
import com.cms.service.StudentService;
import com.cms.vo.ScoreVO;
import com.cms.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Service
public class ScoreServiceImpl extends BaseService<Score> implements ScoreService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public List<ScoreVO> queryList(List<Score> scores){
        List<ScoreVO> scoreVOS = new ArrayList<>();
        for( Score score :scores){
            ScoreVO scoreVO = new ScoreVO();
            scoreVO.setStuname(userMapper.selectByPrimaryKey(scoreVO.getStuId()).getUsername());
            scoreVO.setCoursename(courseInfoMapper.selectByPrimaryKey(scoreVO.getCourseId()).getName());
            scoreVOS.add(scoreVO);
        }

        return scoreVOS;
    }



}
