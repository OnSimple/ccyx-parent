package com.zxwcbj.ccyx.product.service.impl;

import com.zxwcbj.ccyx.product.service.FileUploadService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;


@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 图片上传的方法
     *
     * @param file
     * @return java.lang.String
     **/
    @SneakyThrows
    @Override
    public String fileUpload(MultipartFile file) {
        // 创建minio实例。
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
        // 检查存储桶是否已经存在,如果存储桶不存在，则使用makeBucket方法创建新的存储桶。
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket called 'asiatrip'.
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            System.out.println("Bucket  “ " + bucketName + "   ”already exists.");
        }
        // 上传文件流。
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        //生成随机唯一值，使用uuid，添加到文件名称里面
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + fileName;
        //按照当前日期，创建文件夹，上传到创建文件夹里面
        //  2021/02/02/uuid01.jpg
        String timeUrl = new DateTime().toString("yyyy/MM/dd");
        fileName = timeUrl + "/" + fileName;
        //调用方法实现上传
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, inputStream.available(), -1)
                        .build());
        //上传之后文件路径
        // https://ccyx-zxwcbj.oss-cn-beijing.aliyuncs.com/01.jpg
        String url =  endpoint +"/" +bucketName + "/" +   fileName;
        System.out.println(url);
        //返回
        return url;

    }
}
