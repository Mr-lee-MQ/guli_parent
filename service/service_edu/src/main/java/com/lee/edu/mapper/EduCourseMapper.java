package com.lee.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.vo.CoursePublishVo;
import org.springframework.stereotype.Repository;


/**
* @Entity com.lee.edu.entity.EduCourse
*/
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);
}
