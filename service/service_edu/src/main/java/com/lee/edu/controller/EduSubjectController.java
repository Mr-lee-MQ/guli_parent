package com.lee.edu.controller;


import com.lee.common_utils.R;
import com.lee.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-05-19
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //通过上传过来的文件，把文件内容读取出来
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
    //得到上传过来的Excel文件，使用MultipartFile
        subjectService.saveSubject(file);


        return R.ok();
    }

}

