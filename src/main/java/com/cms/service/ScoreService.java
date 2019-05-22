package com.cms.service;

import com.cms.base.service.IService;
import com.cms.entity.Score;
import com.cms.vo.ScoreVO;

import java.util.List;

/**
 * Created by hs on 2019.5.14.
 */
public interface ScoreService extends IService<Score>{



    List<ScoreVO> queryList(List<Score> scores);

}
