package com.lee.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.common_utils.R;
import com.lee.edu.entity.EduTeacher;
import com.lee.edu.entity.vo.TeacherQuery;
import com.lee.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-17
 */
@Api("讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/edu/edu-teacher")
public class EduTeacherController {


    //1. controller调用service，先注入EduTeacherService的一个对象，service里注入mapper（MP封装了这个过程即调用baseMapper）
    @Autowired
    private EduTeacherService eduTeacherService;
    //edu/edu-teacher/findAll



    //查询讲师表所有数据
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){


    //调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);

        return R.ok().data("item",list) ;
    }


    //逻辑删除{id}通过路径把值传过去
    @ApiOperation("根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师ID",readOnly = true)
            @PathVariable String id){

        boolean flog = eduTeacherService.removeById(id);
        if (flog){
            return R.ok();
        }else {
            return R.error();
        }

    }


    //分页查询
    //current当前页
    //limit每页显示数
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name ="current",value = "当前页码",readOnly = true)
            @PathVariable long current,
            @ApiParam(name = "limit",value = "每页记录数",readOnly = true)
            @PathVariable long limit){

        //首先创建一个page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //调用方法实现分页
        eduTeacherService.page(pageTeacher,null);


        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据List集合

        //返回集合参数方法一
       return R.ok().data("total",total).data("rows",records);

//        Map map = new HashMap();
//
//        map.put("total",total);
//        map.put("rows",records);
//
//        return R.ok().data(map);

    }

    //条件查询带分页
    @ApiOperation("条件查询带分页")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                              @PathVariable long limit,
                              @RequestBody (required = false)
                                              TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建对象
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //wrapper.构建条件,先取出各个条件

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件是否为空，不为空则拼接条件
        if(!StringUtils.isEmpty(name)){
        //构建条件(模糊查询)
            wrapper.like("name",name);
        }

        if (level!=null){
            wrapper.eq("level",level);
        }

        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }

        //排序
        wrapper.orderByDesc("gmt_modified");

        //调用方法实现条件查询带分页
        eduTeacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据List集合
    //返回参数total和rows
        return R.ok().data("total",total).data("rows",records);


    }




    //添加讲师的接口
    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){

        boolean save = eduTeacherService.save(eduTeacher);

        if (save){
            return R.ok();
        }else{
            return R.error();
        }


    }


    //根据讲师ID查询
    @ApiOperation("根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
                return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation("修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flog = eduTeacherService.updateById(eduTeacher);
        if (flog){
            return R.ok();
        }else{
            return R.error();
        }

    }
}

