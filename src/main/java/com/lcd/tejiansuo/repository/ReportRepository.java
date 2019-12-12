package com.lcd.tejiansuo.repository;

import com.lcd.tejiansuo.entity.CheckPointData;
import com.lcd.tejiansuo.entity.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportData,Long> {
}
