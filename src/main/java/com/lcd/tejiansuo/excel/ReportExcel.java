package com.lcd.tejiansuo.excel;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 检测报告sheet
* */
@Data
public class ReportExcel {
  /*报告编号*/
  private String reportNum;
  /*管道名称*/
  private String pipeName;
  /*管线编号*/
  private String pipeNum;
}
