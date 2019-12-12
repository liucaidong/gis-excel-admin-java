package com.lcd.tejiansuo.entity;

import com.lcd.tejiansuo.excel.CheckPointExcel;
import lombok.Data;

import javax.persistence.*;

/*
* 检测点
* */
@Entity
@Table(name = "checkpoint")
@Data
public class CheckPointData {
  public CheckPointData() {

  }

  public CheckPointData(CheckPointExcel excelData){
    this.checkPointName = excelData.getCheckPointName();
    this.checkPointNum = excelData.getCheckPointNum();
    this.lat = excelData.getLat();
    this.lon = excelData.getLon();
    this.photo = excelData.getPhoto();
    this.remark = excelData.getRemark();
    this.type = excelData.getType();
  }

  @Id
  @Column(name="id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /*检测点编号*/
  @Column
  private String checkPointNum;
  /*检测点名称*/
  @Column
  private String checkPointName;
  /*类型*/
  @Column
  private String type;
  /*照片*/
  @Column
  private String photo;
  /*备注*/
  @Column
  private String remark;
  /*经度L*/
  @Column
  private String lon;
  /*纬度B*/
  @Column
  private String lat;
}
