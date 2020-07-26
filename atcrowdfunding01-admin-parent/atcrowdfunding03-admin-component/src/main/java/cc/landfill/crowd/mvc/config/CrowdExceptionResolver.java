package cc.landfill.crowd.mvc.config;

import cc.landfill.crowd.exception.AccessForbiddenException;
import cc.landfill.crowd.exception.LoginAcctAlreadyInUserException;
import cc.landfill.crowd.exception.LoginAcctAlreadyInUserForUpateException;
import cc.landfill.crowd.exception.LoginFailedException;
import cc.landfill.crowd.util.CrowdUtil;
import cc.landfill.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

/**
 * @title: CrowdExceptiomResolver
 * @Author Landfill
 * @Date: 2020/7/24 21:06
 * @Version 1.0
 */

@ControllerAdvice  //异常解析器
public class CrowdExceptionResolver {

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView nullPointerExceptionResolver(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断请求是普通请求还是Ajax请求
        Boolean requestType = CrowdUtil.judgeRequestType(request);
        //如果是Ajax请求 。返回json字符串
        if (requestType){
            //出现异常返回一个已经约定好的处理请求失败的ResultEntity
            ResultEntity<Object> failed = new ResultEntity<>("FAILED", exception.getMessage(), null);
            //使用Gson把ResultEntity转为json字符串
            Gson gson = new Gson();
            String json = gson.toJson(failed);
            response.getWriter().write(json);  //直接使用Servlet原生的response返回，不经过SpringMVC
            return null;
        }
        //如果是普通请求
        //使用ModelAndView存放错误exception对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        //设置跳转到的视图
        modelAndView.setViewName("system-error");
        return modelAndView;
    }

    //抽取方法，针对不同的Exception也可以使用
    private ModelAndView generalExceptionResolver(String viewName,Exception exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断请求是普通请求还是Ajax请求
        Boolean requestType = CrowdUtil.judgeRequestType(request);
        //如果是Ajax请求 。返回json字符串
        if (requestType){
            //出现异常返回一个已经约定好的处理请求失败的ResultEntity
            ResultEntity<Object> failed = new ResultEntity<>("FAILED", exception.getMessage(), null);
            //使用Gson把ResultEntity转为json字符串
            Gson gson = new Gson();
            String json = gson.toJson(failed);
            response.getWriter().write(json);
            return null;
        }
        //如果是普通请求
        //使用ModelAndView存放错误exception对象
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        //设置跳转到的视图
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    //处理数学异常
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView arithmeticExceptionResolver(Exception exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return generalExceptionResolver(viewName,exception,request,response);
    }

    //处理登录失败异常
    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView loginFailedExceptionResolver(Exception exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return generalExceptionResolver(viewName,exception,request,response);
    }
    //处理登录失败异常
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView accessForbiddenExceptionResolver(Exception exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return generalExceptionResolver(viewName,exception,request,response);
    }
      //新增账户已经存在
    @ExceptionHandler(LoginAcctAlreadyInUserException.class)
    public ModelAndView loginAcctAlreadyInUserExceptionResolver(LoginAcctAlreadyInUserException exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-add"; //该页面使用el表达式取出  exception.message
        return generalExceptionResolver(viewName,exception,request,response);
    }
    //更新账户已经存在
    @ExceptionHandler(LoginAcctAlreadyInUserForUpateException.class)
    public ModelAndView loginAcctAlreadyInUserForUpdateExceptionResolver(LoginAcctAlreadyInUserException exception,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return generalExceptionResolver(viewName,exception,request,response);
    }


}