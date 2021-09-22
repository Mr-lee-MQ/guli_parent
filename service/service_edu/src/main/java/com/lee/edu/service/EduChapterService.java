package com.lee.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.edu.entity.EduChapter;
import com.lee.edu.entity.chapter.ChapterVo;

import java.util.List;


/**
*
*/
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    void deleteChapter(String chapterId);
}
