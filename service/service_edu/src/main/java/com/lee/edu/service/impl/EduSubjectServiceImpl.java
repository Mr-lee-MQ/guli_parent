package com.lee.edu.service.impl;


import com.alibaba.excel.EasyExcel;
import com.lee.edu.entity.EduSubject;
import com.lee.edu.entity.excle.SubjectDate;
import com.lee.edu.listener.SubjectExcelListener;
import com.lee.edu.mapper.EduSubjectMapper;
import com.lee.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-05-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取

            EasyExcel.read(in, SubjectDate.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){

        }

    }
}
