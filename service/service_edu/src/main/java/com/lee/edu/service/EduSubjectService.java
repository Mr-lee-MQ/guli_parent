package com.lee.edu.service;

import com.lee.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lee
 * @since 2021-05-19
 */
public interface EduSubjectService extends IService<EduSubject> {


    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    //课程分类列表(树形)
    List<OneSubject> getAllOneTwoSubject();
}
