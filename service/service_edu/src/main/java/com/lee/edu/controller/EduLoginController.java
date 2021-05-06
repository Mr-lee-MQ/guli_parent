package com.lee.edu.controller;

import com.lee.common_utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin  //解决跨域问题
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login方法
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }



    //info方法
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","");
    }


}

