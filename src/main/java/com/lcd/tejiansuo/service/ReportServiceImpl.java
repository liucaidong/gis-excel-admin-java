package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.ReportData;
import com.lcd.tejiansuo.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
  @Autowired
  private ReportRepository reportRepository;

  @Override
  public void deleteAllInBatch(){
    reportRepository.deleteAllInBatch();
  }

  @Override
  public List<ReportData> findAll(){
    return reportRepository.findAll();
  }

  @Override
  public List<ReportData> saveAll(List<ReportData> list) {
    return reportRepository.saveAll(list);
  }
}
