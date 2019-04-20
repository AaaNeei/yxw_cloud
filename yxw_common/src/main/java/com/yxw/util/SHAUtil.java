package com.yxw.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author:阿倪
 * @Date: 2019/3/14 16:38
 * @Description:
 * @return:
 * @throws:
 */
public class SHAUtil {

    public static final String KEY_SHA = "SHA";


    public static  String  getResult(String inputStr)
    {
        BigInteger sha =null;
        System.out.println("=======加密前的数据:"+inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("SHA加密后:" + sha.toString(32));
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }

    public static void main(String args[])
    {
        try {
            String inputStr = "简单加密";
            getResult(inputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
