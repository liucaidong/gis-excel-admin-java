package com.lcd.tejiansuo.repository;

import com.lcd.tejiansuo.entity.CheckPointData;
import com.lcd.tejiansuo.entity.SegmentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentData,Long> {
}
