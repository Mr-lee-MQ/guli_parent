package com.lee.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.edu.entity.EduChapter;
import com.lee.edu.entity.EduVideo;
import com.lee.edu.entity.chapter.ChapterVo;
import com.lee.edu.entity.chapter.VideoVo;
import com.lee.edu.service.EduChapterService;
import com.lee.edu.mapper.EduChapterMapper;
import com.lee.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
*
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService{

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1.根据课程id查询课程里面的所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);


        //2.根据课程id查询课程里面所有的小结
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);


        //创建一个list集合用于存放最终封装的数据
        List<ChapterVo> finalList = new ArrayList<>();
        //3.遍历查询章节list集合进行封装

        for (int i = 0; i <eduChapterList.size() ; i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();

            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);


            List<VideoVo> videoList = new ArrayList<>();
            //4.遍历查询小节list集合，进行封装
            for (int m = 0; m <eduVideoList.size() ; m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断：小节里面chapterid和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }

            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);

        }



        return finalList;
    }
}
