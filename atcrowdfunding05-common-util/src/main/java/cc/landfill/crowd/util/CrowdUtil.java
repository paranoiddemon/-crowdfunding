package cc.landfill.crowd.util;

import cc.landfill.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title: CrowdUtil  工具类
 * @Author Landfill
 * @Date: 2020/7/24 18:12
 * @Version 1.0
 */


public class CrowdUtil {
    /**
    * @Description:  对密码进行MD5加密的工具方法
    * @Param:
    * @return:
    * @Author: Landfill
    * @Date:
    */
    public static String pwdEncryption(String source){
        // 判断是否为空
        if(source == null || source.length()==0){
            // 如果是空字符串就抛异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            // 加密对象MessageDigest
            String algorithm = "MD5";
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            // 需要传入bytes 转为bytes
            byte[] input = source.getBytes();
            // 加密得到一个byte数组，转为16进制
            byte[] output = md5.digest(input);
            // 创建BigInteger signum指定符号为整数
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);
            // 指定为16进制的字符串
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }



    // 判断当前请求是否为Ajax请求
    public static Boolean judgeRequestType(HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        return (acceptHeader !=null && acceptHeader.contains("application/json"))||
                (xRequestHeader !=null && xRequestHeader.equals("XMLHttpRequest"));
    }
}