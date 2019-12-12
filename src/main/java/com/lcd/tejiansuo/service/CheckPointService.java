package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.CheckPointData;

import java.util.List;

public interface CheckPointService {
  public void deleteAllInBatch();

  public List<CheckPointData> findAll();

  public List<CheckPointData> saveAll(List<CheckPointData> list);
}
