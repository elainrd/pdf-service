package com.hhd.pdf.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: DefaultValue
 * Author:   胡侯东
 * Date:     2019-04-24  18:02
 * Description: hhd
 */
@Configuration
public class DefaultValue {

    @Value("${basicUrl}")
    public static String basicUrl = "/home/html/upload/";

    @Value("${fontUrl}")
    public static String fontUrl = "/home/html/font/simkai.ttf";

    static {
        String property = System.getProperties().getProperty("os.name");
        if(property.toLowerCase().contains("Windows".toLowerCase())){
            basicUrl = "E:\\\\fileupload\\\\";
            fontUrl = "c:\\\\windows\\\\fonts\\\\simkai.ttf";
        }
        System.out.println(property);
    }
}
