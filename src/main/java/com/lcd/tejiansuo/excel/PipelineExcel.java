package com.lcd.tejiansuo.excel;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
* 管线编号sheet
* */
@Data
public class PipelineExcel {
  /*管线编号*/
  private String pipeNum;
  /*测量工程名称*/
  private String projectName;
  /*运行介质*/
  private String medium;
  /*压力级制*/
  private String pressLevel;
  /*材质*/
  private String material;
  /*外径(mm)*/
  private String outerDiameter;
  /*长度(m)*/
  private String length;
}
