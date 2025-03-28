package cn.practice.practice01.upload_big_file.service.impl;


import cn.practice.practice01.upload_big_file.po.FileUploadRequest;
import cn.practice.practice01.upload_big_file.template.SliceUploadTemplate;
import cn.practice.practice01.upload_big_file.util.FilePathUtil;
import cn.practice.practice01.upload_big_file.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

@Component
@Slf4j
public class RandomAccessUploadStrategy extends SliceUploadTemplate {

    @Autowired
    private FilePathUtil filePathUtil;

    @Value("${upload.chunkSize}")
    private long defaultChunkSize;

    @Override
    public boolean upload(FileUploadRequest param) {
        RandomAccessFile accessTmpFile = null;
        try {
            String uploadDirPath = filePathUtil.getPath(param);
            File tmpFile = super.createTmpFile(param);
            accessTmpFile = new RandomAccessFile(tmpFile, "rw");
            //这个必须与前端设定的值一致
            long chunkSize = Objects.isNull(param.getChunkSize()) ? defaultChunkSize * 1024 * 1024
                    : param.getChunkSize();
            long offset = chunkSize * param.getChunk();
            //定位到该分片的偏移量
            accessTmpFile.seek(offset);
            //写入该分片数据
            accessTmpFile.write(param.getFile().getBytes());
            boolean isOk = super.checkAndSetUploadProgress(param, uploadDirPath);
            return isOk;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            FileUtil.close(accessTmpFile);
        }


        return false;
    }


}
