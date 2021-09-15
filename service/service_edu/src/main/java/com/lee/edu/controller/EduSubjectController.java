package com.lee.edu.controller;


import com.lee.common_utils.R;
import com.lee.edu.entity.subject.OneSubject;
import com.lee.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        subjectService.saveSubject(file,subjectService);


        return R.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject(){

        //返回的类型为list，泛型中的类为一级分类
        List<OneSubject> list= subjectService.getAllOneTwoSubject();



        return R.ok().data("list",list);
    }


}

