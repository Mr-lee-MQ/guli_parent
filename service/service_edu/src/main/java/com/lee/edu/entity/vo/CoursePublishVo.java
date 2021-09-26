package com.lee.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lee
 * @DATE: 2021/9/23 17:17
 **/
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
