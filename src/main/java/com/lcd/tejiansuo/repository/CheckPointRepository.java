package com.lcd.tejiansuo.repository;

import com.lcd.tejiansuo.entity.CheckPointData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckPointRepository extends JpaRepository<CheckPointData,Long> {
}
