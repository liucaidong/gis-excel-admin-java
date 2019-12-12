package com.lcd.tejiansuo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lcd.tejiansuo.excel.SegmentExcel;
import com.lcd.tejiansuo.entity.SegmentData;
import com.lcd.tejiansuo.service.SegmentService;
import com.lcd.tejiansuo.service.SegmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class SegmentDataListener extends AnalysisEventListener<SegmentExcel> {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(SegmentDataListener.class);
  /**
   * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
   */
  private static final int BATCH_COUNT = 5000;
  List<SegmentData> list = new ArrayList<SegmentData>();
  /**
   * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
   */
  private SegmentService segmentService;

  private SegmentData segmentData;

  public SegmentDataListener() {
    // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//    segmentService = new SegmentServiceImpl();
  }
  public SegmentDataListener(SegmentService segmentService) {
    this.segmentService = segmentService;
  }

  /**
   * 这个每一条数据解析都会来调用
   *
   * @param data
   *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
   * @param context
   */
  @Override
  public void invoke(SegmentExcel data, AnalysisContext context) {
    LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));

    segmentData = new SegmentData(data);

    list.add(segmentData);
    // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
    if (list.size() >= BATCH_COUNT) {
      saveData();
      // 存储完成清理 list
      list.clear();
    }
  }

  /**
   * 所有数据解析完成了 都会来调用
   *
   * @param context
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext context) {
    // 这里也要保存数据，确保最后遗留的数据也存储到数据库
    saveData();
    LOGGER.info("所有数据解析完成！");
  }

  /**
   * 加上存储数据库
   */
  private void saveData() {
    LOGGER.info("{}条数据，开始存储数据库！", list.size());

    try {
      segmentService.deleteAllInBatch();
    }catch (Exception e) {
      LOGGER.info(e.getMessage());
    }finally {
      segmentService.saveAll(list);
    }

    LOGGER.info("存储数据库成功！");
  }
}
