package com.lcd.tejiansuo.service;

import com.lcd.tejiansuo.entity.SegmentData;
import com.lcd.tejiansuo.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegmentServiceImpl implements SegmentService {
  @Autowired
  private SegmentRepository segmentRepository;

  @Override
  public void deleteAllInBatch(){
    segmentRepository.deleteAllInBatch();
  }

  @Override
  public List<SegmentData> findAll(){
    return segmentRepository.findAll();
  }

  @Override
  public List<SegmentData> saveAll(List<SegmentData> list) {
    return segmentRepository.saveAll(list);
  }
}
