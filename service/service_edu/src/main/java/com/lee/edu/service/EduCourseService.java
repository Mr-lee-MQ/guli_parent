package com.lee.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.vo.CourseInfoVo;
import com.lee.edu.entity.vo.CoursePublishVo;
import com.lee.edu.entity.vo.CourseQuery;


/**
*
*/
public interface EduCourseService extends IService<EduCourse> {

    String  saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCouurseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
}
