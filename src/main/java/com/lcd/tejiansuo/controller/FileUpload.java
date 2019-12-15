package com.lcd.tejiansuo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 */
@Controller
public class FileUpload {

  @Value("${file.photoUploadFolder}")
  private String photoUploadFolder;

  @PostMapping(value = "/fileUpload")
  @ResponseBody
  public JSONObject fileUpload(@RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request) throws IOException {
    String reportNum = request.getParameter("reportNum");
//    String currPath = "C:" + File.separator + "photoUpload"+File.separator + reportNum;
    String currPath = photoUploadFolder + reportNum;

    JSONObject res = new JSONObject();
    JSONObject resUrl = new JSONObject();
    JSONArray resUrls = new JSONArray();

    res.put("msg", "");
    res.put("code", 0);

    if (files.length == 0) {
      res.put("msg", "error");
      res.put("code", -1);
      resUrl.put("src", "");
      resUrl.put("name", "");
      resUrls.add(resUrl);
      res.put("data", resUrls);
    }else {
      File pfile = new File(currPath);
      if (!pfile.exists()) {
        pfile.mkdirs();
      }
      for (MultipartFile file : files) {
        //创建本地文件
        File localFile = new File(pfile, file.getOriginalFilename());
//        if (localFile.exists()) {
//          if (localFile.isDirectory()) {
////            目录存在
//          } else {
////            同名文件存在, 不能创建
//          }
//        } else {
////          目录不存在，创建目录
//          localFile.getParentFile().mkdirs();
//        }
        //把传上来的文件写到本地文件
        file.transferTo(localFile);

        resUrl.put("src", reportNum + "/" + file.getOriginalFilename());
        resUrl.put("name", file.getOriginalFilename());
        resUrls.add(resUrl);
      }
      res.put("data", resUrls);
    }

    return res;
  }

}
