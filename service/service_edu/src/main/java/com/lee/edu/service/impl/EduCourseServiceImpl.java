package com.lee.edu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.EduCourseDescription;
import com.lee.edu.entity.vo.CourseInfoVo;
import com.lee.edu.service.EduCourseDescriptionService;
import com.lee.edu.service.EduCourseService;
import com.lee.edu.mapper.EduCourseMapper;
import com.lee.service_base.exceptionhandler.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
implements EduCourseService{

    @Autowired
    private EduCourseDescriptionService CourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1、向课程表中添加课程基本信息

        EduCourse eduCourse = new EduCourse();
        //courseInfoVo对象转换为eduCourse对象
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new GuLiException(20001,"课程信息添加失败");
        }

        String cid  = eduCourse.getId();

        //2、向课程简介表中添加课程简介

        EduCourseDescription courseDescription = new EduCourseDescription();

        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);

        CourseDescriptionService.save(courseDescription);

        return cid;
    }
}
