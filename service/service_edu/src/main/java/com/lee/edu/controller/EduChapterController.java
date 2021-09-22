package com.lee.edu.controller;

import com.lee.common_utils.R;
import com.lee.edu.entity.EduChapter;
import com.lee.edu.entity.EduCourse;
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
    private EduChapterService chapterService;

    //获取课程大纲列表根据id
    @GetMapping("/getChapterVideo/{courseId}")
    public R getAllChapterVideo(@PathVariable String courseId){

       List<ChapterVo> list =  chapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("allChapterVideo",list);
    }
    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);

        return R.ok();
    }
    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);


        return R.ok().data("chapter",eduChapter) ;
    }

    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);

        return R.ok();
    }
    //删除的方法
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){

        chapterService.deleteChapter(chapterId);
        return R.ok();
    }

}
