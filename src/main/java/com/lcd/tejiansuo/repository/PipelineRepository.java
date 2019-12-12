package com.lcd.tejiansuo.repository;

import com.lcd.tejiansuo.entity.CheckPointData;
import com.lcd.tejiansuo.entity.PipelineData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PipelineRepository extends JpaRepository<PipelineData,Long> {
}
