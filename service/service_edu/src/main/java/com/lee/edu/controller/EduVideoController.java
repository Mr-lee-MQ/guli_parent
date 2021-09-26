package com.lee.edu.controller;

import com.lee.common_utils.R;
import com.lee.edu.entity.EduChapter;
import com.lee.edu.entity.EduVideo;
import com.lee.edu.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: lee
 * @DATE: 2021/9/15 16:37
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){

        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        videoService.removeById(id);
        return R.ok();
    }
    //修改小节
    @PostMapping("/updateVideo")
    public R updateChapter(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);

        return R.ok();
    }
    //根据id查询
    @GetMapping("getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);


        return R.ok().data("video",eduVideo) ;
    }
}
