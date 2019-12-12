package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.SegmentData;

import java.util.List;

public interface SegmentService {
  public void deleteAllInBatch();

  public List<SegmentData> findAll();

  public List<SegmentData> saveAll(List<SegmentData> list);
}
