package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.PipelineData;
import com.lcd.tejiansuo.repository.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipelineServiceImpl implements PipelineService {
  @Autowired
  private PipelineRepository pipelineRepository;

  @Override
  public void deleteAllInBatch(){
    pipelineRepository.deleteAllInBatch();
  }

  @Override
  public List<PipelineData> findAll(){
    return pipelineRepository.findAll();
  }

  @Override
  public List<PipelineData> saveAll(List<PipelineData> list) {
    return pipelineRepository.saveAll(list);
  }
}
