package com.lcd.tejiansuo.entity;

import com.lcd.tejiansuo.excel.ReportExcel;
import lombok.Data;

import javax.persistence.*;

/*
* 检测报告sheet
* */
@Entity
@Table(name = "report")
@Data
public class ReportData {
  public ReportData() {

  }

  public ReportData(ReportExcel excelData){
    this.reportNum = excelData.getReportNum();
    this.pipeName = excelData.getPipeName();
    this.pipeNum = excelData.getPipeNum();
  }

  @Id
  @Column(name="id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /*报告编号*/
  @Column
  private String reportNum;
  /*管道名称*/
  @Column
  private String pipeName;
  /*管线编号*/
  @Column
  private String pipeNum;
}
