package com.lee.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
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

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
