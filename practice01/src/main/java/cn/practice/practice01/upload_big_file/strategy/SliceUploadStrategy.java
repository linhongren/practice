package cn.practice.practice01.upload_big_file.strategy;


import cn.practice.practice01.upload_big_file.po.FileUpload;
import cn.practice.practice01.upload_big_file.po.FileUploadRequest;

public interface SliceUploadStrategy {

    FileUpload sliceUpload(FileUploadRequest param);
}
