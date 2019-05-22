package com.cms.controller;

import com.cms.base.annotation.SystemLog;
import com.cms.base.controller.BaseCrudController;
import com.cms.base.query.PageQuery;
import com.cms.entity.CourseInfo;
import com.cms.entity.Score;
import com.cms.entity.Student;
import com.cms.entity.User;
import com.cms.service.*;
import com.cms.util.Result;
import com.cms.vo.ScoreVO;
import com.cms.vo.StudentVO;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2019.5.18.
 */
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseCrudController<Score>{

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseInfoService courseinfoService;

    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("score:view")
    public String rolePage(Model model) {
        model.addAttribute("scoreList", scoreService.queryAll());
        model.addAttribute("courseList",courseinfoService.queryAll());
        model.addAttribute("userList",userService.queryList(new User().setRoleIds("4")));
        return "system/score";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("score:view")
    @Override
    public Result<List<ScoreVO>> queryList(Score score, PageQuery pageQuery) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        if(Integer.valueOf(user.getRoleIds()) == 4){
            score.setStu_id(user.getId());
        }
        Page<Score> page = (Page<Score>) scoreService.queryList(score, pageQuery);
        List<ScoreVO> scoreVOS = new ArrayList<>();
        page.forEach(s -> {
            ScoreVO scoreVO = new ScoreVO(s);
            scoreVO.setStuname(userService.queryById(s.getStu_id()).getUsername());
            scoreVO.setCoursename(courseinfoService.queryById(s.getCourse_id()).getName());
            scoreVOS.add(scoreVO);
        });
        return Result.success(scoreVOS).addExtra("total", page.getTotal());
    }



    @ResponseBody
    @RequiresPermissions("score:create")
    @SystemLog("成绩管理创建")
    @PostMapping("/create")
    @Override
    public Result create(@Validated Score score) {
        scoreService.create(score);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("score:update")
    @SystemLog("成绩管理更新角色")
    @PostMapping("/update")
    @Override
    public Result update(@Validated Score score) {
        scoreService.updateNotNull(score);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("student:delete")
    @SystemLog("成绩管理删除角色")
    @PostMapping("/delete-batch")
    @Override
    public Result deleteBatchByIds(@NotNull @RequestParam("id") Object[] ids) {
        super.deleteBatchByIds(ids);
        return Result.success();
    }

}
