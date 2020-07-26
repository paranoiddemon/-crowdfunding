package cc.landfill.crowd.mvc.interceptor;

import cc.landfill.crowd.constant.CrowdConstant;
import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @title: LoginInterceptor
 * @Author Landfill
 * @Date: 2020/7/25 13:28
 * @Version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    //重写preHandle方法，进行权限的验证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得session
        HttpSession session = request.getSession();
        //尝试从session域中去获取admin
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        //判断是否存在admin
        if (admin == null){
            //不存在就抛异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        //存在admin就放行
        return true;
    }


}