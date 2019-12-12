package com.lcd.tejiansuo.excel;

import lombok.Data;

import javax.persistence.*;

/*
* 检测点
* */
@Data
public class CheckPointExcel {
  /*检测点编号*/
  private String checkPointNum;
  /*检测点名称*/
  private String checkPointName;
  /*类型*/
  private String type;
  /*照片*/
  private String photo;
  /*备注*/
  private String remark;
  /*经度L*/
  private String lon;
  /*纬度B*/
  private String lat;
}
