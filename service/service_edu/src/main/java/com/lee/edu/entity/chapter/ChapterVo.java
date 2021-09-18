package com.lee.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: lee
 * @DATE: 2021/9/17 13:30
 **/
@Data
public class ChapterVo {
    private String id;

    private String title;

    private List<VideoVo> children =  new ArrayList<>();
}
