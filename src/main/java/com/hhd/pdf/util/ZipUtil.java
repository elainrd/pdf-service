package com.hhd.pdf.util;

import com.hhd.pdf.value.DefaultValue;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * FileName: ZipUtil
 * Author:   胡侯东
 * Date:     2019-04-24  17:41
 * Description: hhd
 */
public class ZipUtil {
    public static String urldownloadZip(List<String> fileNamelst, List<String> lstStr) throws Exception {
        //ConfigConstants.ZIPNAME为项目中配置的URL路径
        String fileName =new String(DefaultValue.basicUrl+TimeUtil.getNowTime()+"/"+System.currentTimeMillis()+".zip");
        ZipOutputStream zipos = null;
        zipos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        //设置压缩方法
        zipos.setMethod(ZipOutputStream.DEFLATED);
        DataOutputStream os = null;
        try {
            //循环将文件写入压缩流
            for (int i = 0; i < lstStr.size(); i++) {
                InputStream inputStream = new FileInputStream(lstStr.get(i));
                //添加ZipEntry，并ZipEntry中写入文件流
                zipos.putNextEntry(new ZipEntry(fileNamelst.get(i)));
                os = new DataOutputStream(zipos);
                byte[] buff = new byte[1024*10];
                int len = 0;
                //循环读写
                while ((len=inputStream.read(buff))>-1) {
                    os.write(buff,0,len);
                }
                //关闭此文件流
                inputStream.close();
                //关闭当前ZIP项，并将流放置到写入的位置。下一个条目。
                zipos.closeEntry();
            }
            //释放资源
            os.flush();
            os.close();
            zipos.close();
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //释放资源
            os.close();
            zipos.close();
        }
        return null;
    }
}
