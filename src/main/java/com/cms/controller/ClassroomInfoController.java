package com.cms.controller;

import com.cms.entity.ClassroomInfo;
import com.cms.service.ClassroomInfoService;
import com.cms.util.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/classroomInfo")
public class ClassroomInfoController {

    @Autowired
    private ClassroomInfoService classroomInfoService;

    /**
     * 新增一条教室信息
     * @param classroomInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public MsgUtil insert(ClassroomInfo classroomInfo){
        return  classroomInfoService.insert(classroomInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     *根据ID修改教室信息
     * @param classroomInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateById")
    public MsgUtil update(ClassroomInfo classroomInfo){
        return  classroomInfoService.updateByPrimartKey(classroomInfo) == 1 ? MsgUtil.success() : MsgUtil.error();
    }

    /**
     * 根据ID删除教室信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delById")
    public MsgUtil delById(int id){
        return classroomInfoService.deleteByPrimaryKey(id) == 1 ? MsgUtil.success() : MsgUtil.error();
    }


    /**
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<ClassroomInfo> queryList() {
        List<ClassroomInfo> classroominfos = classroomInfoService.selectAll();
        return classroominfos;
    }


    /**
     * 跳转页面
     * @return 返回页面
     */
    @RequestMapping("/list")
    public String selectAll(){
        return "ClassroomInfoManage/ClassroomInfo";
    }

}
