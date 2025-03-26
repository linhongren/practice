package cn.practice.practice01.upload_big_file.context;



import cn.practice.practice01.upload_big_file.annotation.UploadMode;
import cn.practice.practice01.upload_big_file.enu.UploadModeEnum;
import cn.practice.practice01.upload_big_file.service.impl.RandomAccessUploadStrategy;
import cn.practice.practice01.upload_big_file.strategy.SliceUploadStrategy;
import cn.practice.practice01.upload_big_file.util.SpringContextHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public enum UploadContext {
   INSTANCE;

   private static final String PACKAGE_NAME = "com.example.upload_big_file";

   private Map<UploadModeEnum,Class<SliceUploadStrategy>> uploadStrategyMap = new ConcurrentHashMap<>();


   public void init(){
     Reflections reflections = new Reflections(PACKAGE_NAME);
     Set<Class<?>> clzSet = reflections.getTypesAnnotatedWith(UploadMode.class);
     if(CollectionUtils.isNotEmpty(clzSet)){
       for (Class<?> clz : clzSet) {
         UploadMode uploadMode = clz.getAnnotation(UploadMode.class);
         uploadStrategyMap.put(uploadMode.mode(), (Class<SliceUploadStrategy>) clz);
       }
     }
   }

   public SliceUploadStrategy getInstance(UploadModeEnum mode){
     return this.getStrategyByType(mode);

   }


   private SliceUploadStrategy getStrategyByType(UploadModeEnum mode){
//     Class<SliceUploadStrategy> clz = uploadStrategyMap.get(mode);
//     Assert.notNull(clz,"mode:"+mode+"can not found class,please checked");
//       Class<SliceUploadStrategy> clz = null;
//       if(mode == UploadModeEnum.RANDOM_ACCESS){
//           clz = (Class<SliceUploadStrategy>)RandomAccessUploadStrategy.class;
//       }else if(mode == UploadModeEnum.MAPPED_BYTEBUFFER){
//           clz = (Class<SliceUploadStrategy>)MappedByteBufferUploadStrategy.class;
//       }
     return SpringContextHolder.getBean(RandomAccessUploadStrategy.class);
   }

}
