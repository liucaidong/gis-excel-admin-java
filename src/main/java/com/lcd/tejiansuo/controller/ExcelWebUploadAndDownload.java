package com.lcd.tejiansuo.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lcd.tejiansuo.entity.ReportData;
import com.lcd.tejiansuo.excel.*;
import com.lcd.tejiansuo.listener.*;
import com.lcd.tejiansuo.service.CheckPointService;
import com.lcd.tejiansuo.service.PipelineService;
import com.lcd.tejiansuo.service.ReportService;
import com.lcd.tejiansuo.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;


@Controller
public class ExcelWebUploadAndDownload {
  @Autowired
  private CheckPointService checkPointService;
  @Autowired
  private ReportService reportService;
  @Autowired
  private PipelineService pipelineService;
  @Autowired
  private SegmentService segmentService;

  /**
   * 文件下载（失败了会返回一个有部分数据的Excel）
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link ReportData}
   * <p>
   * 2. 设置返回的 参数
   * <p>
   * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
   */
  @GetMapping("download")
  public void download(HttpServletResponse response) throws IOException {
    // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
    String fileName = URLEncoder.encode("测试", "UTF-8");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), ReportData.class).sheet("模板").doWrite(data());
  }

  /**
   * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
   *
   * @since 2.1.1
   */
  @GetMapping("downloadFailedUsingJson")
  public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
    // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
    try {
      response.setContentType("application/vnd.ms-excel");
      response.setCharacterEncoding("utf-8");
      // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
      String fileName = URLEncoder.encode("测试", "UTF-8");
      response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
      // 这里需要设置不关闭流
      EasyExcel.write(response.getOutputStream(), ReportData.class).autoCloseStream(Boolean.FALSE).sheet("模板")
          .doWrite(data());
    } catch (Exception e) {
      // 重置response
      response.reset();
      response.setContentType("application/json");
      response.setCharacterEncoding("utf-8");
      Map<String, String> map = new HashMap<String, String>();
      map.put("status", "failure");
      map.put("message", "下载文件失败" + e.getMessage());
      response.getWriter().println(JSON.toJSONString(map));
    }
  }

  /**
   * 文件上传
   * <p>
   * 1. 创建excel对应的实体对象 参照{@link ReportData}
   * <p>
   * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReportDataListener}
   * <p>
   * 3. 直接读即可
   */
  @PostMapping("upload")
  @ResponseBody
  public String upload(MultipartFile file) throws IOException {
    // 读取全部sheet
    // 这里需要注意 DemoDataListener的doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
//    EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener()).doReadAll();

    // 读取部分sheet
    ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build();
    // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
    ReadSheet readSheet1 =
        EasyExcel.readSheet(0).head(ReportExcel.class).registerReadListener(new ReportDataListener(reportService)).build();
    ReadSheet readSheet2 =
        EasyExcel.readSheet(1).head(CheckPointExcel.class).registerReadListener(new CheckPointDataListener(checkPointService)).build();
    ReadSheet readSheet3 =
        EasyExcel.readSheet(2).head(PipelineExcel.class).registerReadListener(new PipelineDataListener(pipelineService)).build();
    ReadSheet readSheet4 =
        EasyExcel.readSheet(3).head(SegmentExcel.class).registerReadListener(new SegmentDataListener(segmentService)).build();
    // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
    excelReader.read(readSheet1, readSheet2, readSheet3, readSheet4);
    // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
    excelReader.finish();

//    EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener(uploadDAO)).sheet().doRead();
    return "success";
  }

  private List<ReportData> data() {
    List<ReportData> list = new ArrayList<ReportData>();
    for (int i = 0; i < 10; i++) {
      ReportData data = new ReportData();
      data.setPipeName("a");
      data.setPipeNum("b");
      data.setReportNum("c");
      list.add(data);
    }
    return list;
  }
}
