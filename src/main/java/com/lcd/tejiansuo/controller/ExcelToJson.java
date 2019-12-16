package com.lcd.tejiansuo.controller;


import com.lcd.tejiansuo.entity.CheckPointData;
import com.lcd.tejiansuo.entity.PipelineData;
import com.lcd.tejiansuo.entity.ReportData;
import com.lcd.tejiansuo.entity.SegmentData;
import com.lcd.tejiansuo.service.CheckPointService;
import com.lcd.tejiansuo.service.PipelineService;
import com.lcd.tejiansuo.service.ReportService;
import com.lcd.tejiansuo.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/excel", method = RequestMethod.GET)
public class ExcelToJson {
  @Autowired
  private CheckPointService checkPointService;
  @Autowired
  private ReportService reportService;
  @Autowired
  private PipelineService pipelineService;
  @Autowired
  private SegmentService segmentService;

  @RequestMapping(value = "/checkpoint", method = RequestMethod.GET)
  public List<CheckPointData> checkpoint() throws IOException {
    return checkPointService.findAll();
  }

  @RequestMapping(value = "/report", method = RequestMethod.GET)
  public List<ReportData> report() throws IOException {
    return reportService.findAll();
  }

  @RequestMapping(value = "/pipeline", method = RequestMethod.GET)
  public List<PipelineData> pipeline() throws IOException {
    return pipelineService.findAll();
  }

  @RequestMapping(value = "/segment", method = RequestMethod.GET)
  public List<SegmentData> segment() throws IOException {
    return segmentService.findAll();
  }

}
