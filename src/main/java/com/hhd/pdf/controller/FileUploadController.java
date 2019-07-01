package com.hhd.pdf.controller;

import com.hhd.pdf.util.InputStreamUtil;
import com.hhd.pdf.util.PdfUtil;
import com.hhd.pdf.util.TimeUtil;
import com.hhd.pdf.util.ZipUtil;
import com.hhd.pdf.value.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: FileUploadController
 * Author:   胡侯东
 * Date:     2019-04-24  13:53
 * Description: hhd
 */

@RestController
public class FileUploadController {

    @RequestMapping("/fileUploadController")
    @ResponseBody
    public Object fileUpload(MultipartFile[] filenames, HttpServletRequest request) throws Exception{
        List<String> names = new ArrayList<>();
        List<String> filePathLocal = new ArrayList<>();
        for(MultipartFile filename:filenames) {
            names.add(filename.getOriginalFilename());
            String path = DefaultValue.basicUrl+ TimeUtil.getNowTime();
            File file = new File(path);
            //如果路径不存在，新建
            if(!file.exists()&&!file.isDirectory()) {
                file.mkdirs();
            }
            //输出文件
            String localPath = path + "\\" + filename.getOriginalFilename();
            filePathLocal.add(localPath);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(localPath)));
            ByteArrayInputStream iame = InputStreamUtil.getImageStream("http://www.huhdcc.top:8001/static/sm_pdf_service/sannianyimeng.png");
            byte b[] = new byte[iame.available()];
            iame.read(b,0,iame.available());
            String desc = "时间：" + TimeUtil.getNowTime() + " QQ群：1016692522";
            PdfUtil.setWatermark(out, new ByteArrayInputStream(filename.getBytes()), desc, b);
        }
        //输出zip
        String urldownloadZip = ZipUtil.urldownloadZip(names, filePathLocal);
        System.out.println("zip 文件地址 urldownloadZip= "+urldownloadZip);
        urldownloadZip = urldownloadZip.replaceAll("/usr/local/java/resources/static/sm_pdf_service", "http://www.huhdcc.top:8001/static/sm_pdf_service");
        System.out.println("nginx 地址 path = "+urldownloadZip);
        return urldownloadZip;
    }
}
