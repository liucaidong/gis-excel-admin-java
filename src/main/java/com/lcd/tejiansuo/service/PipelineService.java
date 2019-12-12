package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.PipelineData;

import java.util.List;

public interface PipelineService {
  public void deleteAllInBatch();

  public List<PipelineData> findAll();

  public List<PipelineData> saveAll(List<PipelineData> list);
}
