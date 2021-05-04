package com.lee.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lee.oss.service.OssService;
import com.lee.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {

    //上传头像到Oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //通过工具类获取endpoint，accessKeyId，accessKeySecret，bucketName几个参数
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


            //获取文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();
            //调用oss方法实现上传
            //第一个参数  bucket名称
            //第二个参数  上传到oss文件路径和文件名称
            //第三个参数  上传文件输入流

            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接起来
            //观察路径 https://edu-0128.oss-cn-beijing.aliyuncs.com/body.png
            return "https://"+bucketName+"."+endpoint+"/"+fileName;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
