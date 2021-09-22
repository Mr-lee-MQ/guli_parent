package com.lee.edu.controller;

import com.lee.common_utils.R;
import com.lee.edu.entity.vo.CourseInfoVo;
import com.lee.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lee
 * @DATE: 2021/9/15 16:37
 **/
@Api("课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加后的课程id，为了后面添加课程大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }


    //根据课程id查询课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);


        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        courseService.updateCouurseInfo(courseInfoVo);
        return R.ok();
    }


}
