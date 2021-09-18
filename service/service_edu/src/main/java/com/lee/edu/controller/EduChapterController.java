package com.lee.edu.controller;

import com.lee.common_utils.R;
import com.lee.edu.entity.chapter.ChapterVo;
import com.lee.edu.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: lee
 * @DATE: 2021/9/15 16:36
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    @GetMapping("/getChapterVideo/{courseId}")
    public R getAllChapterVideo(@PathVariable String courseId){

       List<ChapterVo> list =  eduChapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("allChapterVideo",list);
    }
}
