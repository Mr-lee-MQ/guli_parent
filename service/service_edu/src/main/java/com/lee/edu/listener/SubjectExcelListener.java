package com.lee.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.edu.entity.EduSubject;
import com.lee.edu.entity.excle.SubjectDate;
import com.lee.edu.service.EduSubjectService;
import com.lee.service_base.exceptionhandler.GuLiException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectDate> {

    //因为SubjectExcelListener需要手动new，所以不能交给Spring管理，也不能注入其他对象
    //解决方案：手动注入EduSubjectService

    public EduSubjectService subjectService;
    //有参
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    //无参
    public SubjectExcelListener() {
    }

    //读取Excel内容，一行一行读取
    @Override
    public void invoke(SubjectDate subjectDate, AnalysisContext analysisContext) {
        if (subjectDate==null){
            throw new GuLiException(20001,"Excel文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值为一级分类，第二个值为二级分类
        //判断一级分类是否重复
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectDate.getOneSubjectName());
        if (oneSubject==null){//没有相同一级分类，进行添加

        }


    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);

    }


    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);

    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
