package com.lee.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.common_utils.R;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.vo.CourseInfoVo;
import com.lee.edu.entity.vo.CoursePublishVo;
import com.lee.edu.entity.vo.CourseQuery;
import com.lee.edu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
    //最终发布课程
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){

        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }
    //课程列表（未实现条件查询带分页）
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }





}
