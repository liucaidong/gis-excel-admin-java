package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.CheckPointData;
import com.lcd.tejiansuo.repository.CheckPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPointServiceImpl implements CheckPointService {
  @Autowired
  private CheckPointRepository checkPointRepository;

  @Override
  public void deleteAllInBatch(){
    checkPointRepository.deleteAllInBatch();
  }

  @Override
  public List<CheckPointData> findAll(){
    return checkPointRepository.findAll();
  }

  @Override
  public List<CheckPointData> saveAll(List<CheckPointData> list) {
    return checkPointRepository.saveAll(list);
  }
}
