package cn.practice.practice01.upload_big_file.callable;



import cn.practice.practice01.upload_big_file.context.UploadContext;
import cn.practice.practice01.upload_big_file.enu.UploadModeEnum;
import cn.practice.practice01.upload_big_file.po.FileUpload;
import cn.practice.practice01.upload_big_file.po.FileUploadRequest;

import java.util.concurrent.Callable;

public class FileCallable implements Callable<FileUpload> {

  private UploadModeEnum mode;

  private FileUploadRequest param;

  public FileCallable(UploadModeEnum mode,
                      FileUploadRequest param) {

    this.mode = mode;
    this.param = param;
  }

  @Override
  public FileUpload call() throws Exception {
    System.out.println("333333");

//    FileUpload fileUploadDTO = UploadContext.INSTANCE.getInstance(mode).sliceUpload(param);
    FileUpload fileUploadDTO = UploadContext.INSTANCE.getInstance(mode).sliceUpload(param);
    return fileUploadDTO;
  }
}
