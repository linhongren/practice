package cn.practice.practice01.upload_big_file.service;





import cn.practice.practice01.upload_big_file.po.FileUpload;
import cn.practice.practice01.upload_big_file.po.FileUploadRequest;

import java.io.IOException;

public interface FileService {

  FileUpload upload(FileUploadRequest fileUploadRequestDTO)throws IOException;

  FileUpload sliceUpload(FileUploadRequest fileUploadRequestDTO);

  FileUpload checkFileMd5(FileUploadRequest fileUploadRequestDTO)throws IOException;

}
