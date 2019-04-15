package com.cms.controller;


import com.cms.entity.ClassInfo;
import com.cms.service.ClassInfoService;
import com.cms.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/classInfo")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    /**
     * 新增一条班级信息
     * @param classInfo
     * @returnC
     */
    @ResponseBody
    @RequestMapping("/add")
    public MsgUtil insert(ClassInfo classInfo){
        return  classInfoService.insert(classInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     *根据ID修改班级信息
     * @param classInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateById")
    public MsgUtil update(ClassInfo classInfo){
        return  classInfoService.updateByPrimartKey(classInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     * 根据ID删除班级信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delById")
    public MsgUtil delById(int id){
        return classInfoService.deleteByPrimaryKey(id) == 1 ? MsgUtil.success() : MsgUtil.error();
    }


    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ClassInfo> queryList() {
        List<ClassInfo> classinfos = classInfoService.selectAll();
        return classinfos;
    }


    /**
     * 跳转页面
     * @return 返回页面
     */
    @RequestMapping("/list")
    public String selectAll(){
        return "ClassInfoManage/ClassInfo";
    }
}
