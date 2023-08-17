package com.zxwcbj.ccyx.product.controller;

import com.zxwcbj.ccyx.common.result.Result;
import com.zxwcbj.ccyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@CrossOrigin
@RequestMapping("admin/product")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    //文件上传,图片上传的方法
    @ApiOperation("图片上传")
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws Exception {
        return Result.ok(fileUploadService.fileUpload(file));
    }
}
