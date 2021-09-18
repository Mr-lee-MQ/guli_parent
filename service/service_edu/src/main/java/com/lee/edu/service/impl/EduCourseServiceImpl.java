package com.lee.edu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.edu.entity.EduCourse;
import com.lee.edu.entity.EduCourseDescription;
import com.lee.edu.entity.vo.CourseInfoVo;
import com.lee.edu.service.EduCourseDescriptionService;
import com.lee.edu.service.EduCourseService;
import com.lee.edu.mapper.EduCourseMapper;
import com.lee.service_base.exceptionhandler.GuLiException;
import com.sun.java.accessibility.util.GUIInitializedListener;
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
    private EduCourseDescriptionService courseDescriptionService;

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

        courseDescriptionService.save(courseDescription);

        return cid;
    }

    //根据课程id查询课程信息
    @Override
    public CourseInfoVo getgetCourseInfo(String courseId) {

        //1.查询课程表

        EduCourse eduCourse = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);



        //2.查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());


        return courseInfoVo;
    }
    //修改方法
    @Override
    public void updateCouurseInfo(CourseInfoVo courseInfoVo) {

        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int update = baseMapper.updateById(eduCourse);
        if (update == 0){
            throw new GuLiException(20001,"修改课程信息失败");
        }

        //修改描述表

        EduCourseDescription description = new EduCourseDescription();

        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());

        courseDescriptionService.updateById(description);

    }
}
