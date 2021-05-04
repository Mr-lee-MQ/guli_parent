package com.lee.oss.controller;


import com.lee.common_utils.R;
import com.lee.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;


    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获得上传的头像文件，通过MultipartFile file
        //返回上传到Oss中文件的路径
        String url =ossService.uploadFileAvatar(file);





        return R.ok().data("url",url);
    }

}
