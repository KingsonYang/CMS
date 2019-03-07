package com.cms.controller;

import com.cms.entity.CourseInfo;
import com.cms.entity.custom.PageEntity;
import com.cms.service.CourseInfoService;
import com.cms.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courseInfo")
public class CourseInfoController {

    @Autowired
    private CourseInfoService courseInfoService;

    Map map = new HashMap();

    /**
     * 新增一条数据
     * @param courseInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public MsgUtil insert(CourseInfo courseInfo){
        return  courseInfoService.insert(courseInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     *根据ID修改课程信息
     * @param courseInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateById")
    public MsgUtil update(CourseInfo courseInfo){
        return  courseInfoService.updateByPrimartKey(courseInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     * 根据ID删除课程信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delById")
    public MsgUtil delById(int id){
        int flag = courseInfoService.deleteByPrimaryKey(id);
        if(flag == 1){
            return MsgUtil.success();
        }else{
            return MsgUtil.error();
        }
    }

    /**
     * 获取课程信息分页显示
     * @param courseInfo
     * @return 返回json数组
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public ResponseEntity queryList(CourseInfo courseInfo,Model model) {
        List<CourseInfo> courseInfos = courseInfoService.selectAll(courseInfo);
        model.addAttribute("courseInfoes",new PageEntity<>(courseInfos));
        return new ResponseEntity( new PageEntity<>(courseInfos), HttpStatus.OK);
    }

    /**
     * 获取课程信息分页显示
     * @param model
     * @param courseInfo
     * @return 返回页面
     */
    @RequestMapping("/list")
    public String selectAll(Model model,CourseInfo courseInfo){
        List<CourseInfo> list = courseInfoService.selectAll(courseInfo);
//        model.addAttribute("courseInfoList",list);
        model.addAttribute("courseInfos",new PageEntity<>(list));
        return "CourseInfoManage/CourseInfo";
    }

}
