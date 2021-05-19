package com.lee.edu.entity.excle;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

//Excel的实体类
@Data
public class SubjectDate {

    //一级目录
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //二级目录
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
