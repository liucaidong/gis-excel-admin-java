package com.lcd.tejiansuo.excel;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 管段sheet
* */
@Data
public class SegmentExcel {
  /*管段编号*/
  private String segmentNum;
  /*起点经度L*/
  private String startL;
  /*起点纬度B*/
  private String startB;
  /*终点经度L*/
  private String endL;
  /*终点纬度B*/
  private String endB;
  /*管线编号*/
  private String pipeNum;
  /*压力级制*/
  private String pressLevel;
  /*平面长度(m)*/
  private String length;
}
