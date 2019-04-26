package com.hhd.pdf.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * FileName: InputStreamUtil
 * Author:   胡侯东
 * Date:     2019-04-24  10:52
 * Description: hhd
 */
public class InputStreamUtil {

    public static ByteArrayInputStream getImageStream(String filePath) {
        // 输入流对象
        InputStream in = null;
        // 字节数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            URL url = new URL(filePath);
            URLConnection connection = url.openConnection();
            in = connection.getInputStream();

            int len = -1;
            byte[] data = new byte[1024];
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
        } catch (MalformedURLException me) {
            //logger.error("创建链接失败,message={}", me.getMessage());
            //throw new LiefengException(me);
        } catch (IOException ie) {
            //logger.error("IO操作失败,message={}", ie.getMessage());
           // throw new LiefengException(ie);
        } finally {
            if (in != null) {
                try {
                    // 关闭输入流
                    in.close();
                } catch (IOException e) {
                   // logger.error("输入流关闭失败,message={}", e.getMessage());
                    //throw new LiefengException(e);
                }
            }
            if (out != null) {
                try {
                    // 关闭输出流
                    out.close();
                } catch (IOException e) {
                   // logger.error("输出流关闭失败,message={}", e.getMessage());
                   // throw new LiefengException(e);
                }
            }
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
