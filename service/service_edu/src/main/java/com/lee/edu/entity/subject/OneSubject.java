package com.lee.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lee
 * @DATE: 2021/9/14 17:55
 **/
@Data
public class OneSubject {
    //一级分类
    private String id;
    private String title;
    //一个一级分类中含有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
