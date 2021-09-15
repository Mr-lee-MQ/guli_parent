package com.lee.edu.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.edu.entity.EduSubject;
import com.lee.edu.entity.excle.SubjectDate;
import com.lee.edu.entity.subject.OneSubject;
import com.lee.edu.entity.subject.TwoSubject;
import com.lee.edu.listener.SubjectExcelListener;
import com.lee.edu.mapper.EduSubjectMapper;
import com.lee.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
                e.printStackTrace();
        }

    }
    //课程分类列表(树形)
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1、查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2、查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建一个集合，用于存放所有一二级分类
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3、封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值。
        //封装到要求的list集合里面finalSubjectList

        for (int i = 0; i < oneSubjectList.size(); i++) {//遍历oneSubjectList
            EduSubject eduSubject = oneSubjectList.get(i);

            OneSubject oneSubject = new OneSubject();

//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            //一种简化写法，把eduSubject中的值set到oneSubject中
            BeanUtils.copyProperties(eduSubject,oneSubject);


            //将遍历出来的一级分类放到finalSubjectList中
            finalSubjectList.add(oneSubject);

            //在一级分类中循环遍历查询所有的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类中的list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {

                //获取遍历出的每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类属于哪个一级分类
                if (tSubject.getParentId().equals(oneSubject.getId())){

                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);

                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }


        //4、封装二级分类
        return finalSubjectList;
    }
}
