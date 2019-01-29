package com.cms.dao;

import com.cms.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Score score);

    Score selectByPrimaryKey(Integer id);

    List<Score> selectAll();

    int updateByPrimaryKey(Score score);
}
