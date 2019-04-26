package com.hhd.pdf.util;

import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * FileName: Md5Util
 * Author:   胡侯东
 * Date:     2019-04-24  11:25
 * Description: hhd
 */
public class Md5Util {

    /**
     * 加密密码
     */
    private static String PASSWORD = "SANMENG";

    /**
     * 加密种子
     */
    private static String SALT = "1016692522";

    /**
     * 普通MD5加密
     * @param inStr
     * @return
     */
    public static String getStrMD5(String inStr) {
        // 获取MD5实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }

        // 将加密字符串转换为字符数组
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        // 开始加密
        for(int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int var = digest[i] & 0xff;
            if (var < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(Hex.encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }

    public static String getMD5String(String str) throws NoSuchAlgorithmException {
        byte[] res = str.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5".toUpperCase());
        byte[] result = md.digest(res);
        for (int i = 0; i < result.length; i++) {
            md.update(result[i]);
        }
        byte[] hash = md.digest();
        StringBuffer d = new StringBuffer("");
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16){
                d.append("0");
            }
            d.append(Integer.toString(v, 16).toUpperCase() + "");
        }
        return d.toString();
    }

    public static String MD5() throws NoSuchAlgorithmException {
        byte[] res = (PASSWORD+SALT).getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5".toUpperCase());
        byte[] result = md.digest(res);
        for (int i = 0; i < result.length; i++) {
            md.update(result[i]);
        }
        byte[] hash = md.digest();
        StringBuffer d = new StringBuffer("");
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16){
                d.append("0");
            }
            d.append(Integer.toString(v, 16).toUpperCase() + "");
        }
        return d.toString();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String saltMD5 = MD5();
        System.out.println(saltMD5);
    }
}
