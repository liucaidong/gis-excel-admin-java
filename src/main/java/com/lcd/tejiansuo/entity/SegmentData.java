package com.lcd.tejiansuo.entity;

import com.lcd.tejiansuo.excel.SegmentExcel;
import lombok.Data;

import javax.persistence.*;

/*
* 管段sheet
* */
@Entity
@Table(name = "segment")
@Data
public class SegmentData {
  public SegmentData() {

  }

  public SegmentData(SegmentExcel excelData){
    this.segmentNum = excelData.getSegmentNum();
    this.startL = excelData.getStartL();
    this.startB = excelData.getStartB();
    this.endL = excelData.getEndL();
    this.endB = excelData.getEndB();
    this.pipeNum = excelData.getPipeNum();
    this.pressLevel = excelData.getPressLevel();
    this.length = excelData.getLength();
  }

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /*管段编号*/
  @Column
  private String segmentNum;
  /*起点经度L*/
  @Column
  private String startL;
  /*起点纬度B*/
  @Column
  private String startB;
  /*终点经度L*/
  @Column
  private String endL;
  /*终点纬度B*/
  @Column
  private String endB;
  /*管线编号*/
  @Column
  private String pipeNum;
  /*压力级制*/
  @Column
  private String pressLevel;
  /*平面长度(m)*/
  @Column
  private String length;
}
