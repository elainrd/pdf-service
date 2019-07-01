package com.hhd.pdf.util;

import com.hhd.pdf.value.DefaultValue;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import org.dom4j.DocumentException;

import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * FileName: PdfUtil
 * Author:   胡侯东
 * Date:     2019-04-23  17:11
 * Description: hhd
 */
public class PdfUtil {

    /**
     *
     * @param bos 文件输出流
     * @param input 文件输入流
     * @param desc 底部描述
     * @param images 标志水印
     * @throws DocumentException
     * @throws IOException
     * @throws com.itextpdf.text.DocumentException
     */
    public static void setWatermark(BufferedOutputStream bos, ByteArrayInputStream input,String desc,byte images[]) throws DocumentException, IOException, com.itextpdf.text.DocumentException, NoSuchAlgorithmException {
        PdfReader reader = null ;
        try {
            reader = new PdfReader(input);
        }catch (Exception e) {
            System.out.println(input+"失败....");
            return;
        }
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content = null;
        BaseFont base = BaseFont.createFont(DefaultValue.fontUrl, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        stamper.setEncryption(null, Md5Util.MD5().getBytes(), com.itextpdf.text.pdf.PdfWriter.ALLOW_PRINTING, false);
        for (int i = 1; i < total; i++) {
            Image image = Image.getInstance(images);
            image.scalePercent(100);
            image.setAlignment(Image.UNDERLYING);
            float width = reader.getPageSize(i).getWidth();
            image.setAbsolutePosition(10, 10);
            image.scaleAbsolute(30,30);
            content = stamper.getUnderContent(i);
            content.addImage(image);
            gs.setFillOpacity(0.5f);
            content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.LIGHT_GRAY);
            content.setFontAndSize(base, 150);
            content.setTextMatrix(70, 200);
            content.setColorFill(BaseColor.BLACK);
            content.setFontAndSize(base, 8);
            System.out.println("width = "+width);
            content.showTextAligned(Element.ALIGN_CENTER, desc, width/2, 10, 0);
            content.endText();
        }
        stamper.close();
    }
}
