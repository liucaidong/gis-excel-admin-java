package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.ReportData;

import java.util.List;

public interface ReportService {
  public void deleteAllInBatch();

  public List<ReportData> findAll();

  public List<ReportData> saveAll(List<ReportData> list);
}
