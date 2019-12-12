package com.lcd.tejiansuo.entity;

import com.lcd.tejiansuo.excel.PipelineExcel;
import lombok.Data;

import javax.persistence.*;

/*
* 管线编号sheet
* */
@Entity
@Table(name = "pipeline")
@Data
public class PipelineData {
  public PipelineData() {

  }

  public PipelineData(PipelineExcel excelData){
    this.pipeNum = excelData.getPipeNum();
    this.projectName = excelData.getProjectName();
    this.medium = excelData.getMedium();
    this.pressLevel = excelData.getPressLevel();
    this.material = excelData.getMaterial();
    this.outerDiameter = excelData.getOuterDiameter();
    this.length = excelData.getLength();
  }

  @Id
  @Column(name="id")
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  /*管线编号*/
  @Column
  private String pipeNum;
  /*测量工程名称*/
  @Column
  private String projectName;
  /*运行介质*/
  @Column
  private String medium;
  /*压力级制*/
  @Column
  private String pressLevel;
  /*材质*/
  @Column
  private String material;
  /*外径(mm)*/
  @Column
  private String outerDiameter;
  /*长度(m)*/
  @Column
  private String length;
}
