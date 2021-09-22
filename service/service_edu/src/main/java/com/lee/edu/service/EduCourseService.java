package com.lee.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.vo.CourseInfoVo;





/**
*
*/
public interface EduCourseService extends IService<EduCourse> {

    String  saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCouurseInfo(CourseInfoVo courseInfoVo);
}
