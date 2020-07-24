package cc.landfill.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: CrowdUtil
 * @Author Landfill
 * @Date: 2020/7/24 18:12
 * @Version 1.0
 */
//判断当前请求是否为Ajax请求
public class CrowdUtil {
    public static Boolean judgeRequestType(HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        return (acceptHeader !=null && acceptHeader.contains("application/json"))||
                (xRequestHeader !=null && xRequestHeader.equals("XMLHttpRequest"));
    }
}