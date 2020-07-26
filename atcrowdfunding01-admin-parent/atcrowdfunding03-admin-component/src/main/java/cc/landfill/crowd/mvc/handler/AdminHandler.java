package cc.landfill.crowd.mvc.handler;

import cc.landfill.crowd.constant.CrowdConstant;
import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.service.api.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @title: AdminHandler  处理用户登录 校验 退出 的控制器
 * @Author Landfill
 * @Date: 2020/7/25 12:18
 * @Version 1.0
 */

@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;
    /**
    * @Description:  用户登录的方法
    * @Param:
    * @return:
    * @Author: Landfill
    * @Date:
    */
    @RequestMapping("/admin/do/login.html")
    public String adminLogin(HttpSession session,
                             @RequestParam("loginAcct") String loginAcct,
                             @RequestParam("userPswd") String userPswd
                             ){

        //从数据库查询到admin,如果返回对象说明登录成功，如果失败会在Service里抛出异常，主要逻辑在Service实现类里处理
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);
        //把得到的admin对象存入session
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        //重定向到登录后的后台页面,因为如果使用转发，刷新会重新提交表单。在配置增加view-controller
        return "redirect:/admin/to/main/page.html";
    }
    /**
    * @Description:  用户退出登录。
    * @Param:
    * @return:
    * @Author: Landfill
    * @Date:
    */

    @RequestMapping("/admin/do/logout.html")
    public String adminLogout(HttpSession session){
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html"; //重定向到登录页面
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                     ModelMap modelMap){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        //把pageInfo对象转发
        modelMap.addAttribute("pageInfo",pageInfo);
        modelMap.addAttribute("msg","testEL 你好呀");

        return "admin-page";

    }
    //删除
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html") //从请求的url中取出变量，不要加$
    public String remove(@PathVariable("adminId") Integer adminId,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") String keyword
                         ){
        adminService.remove(adminId);

        //跳转回来还是想到原来的分页页面。 但是没有携带参数（pagesize和pagenum，该列表没有分页结果。要保持原有的参数
        //return "admin-page";
        //return "forward:/admin/get/page.html"; //转发请求到该页面，和上面的方式的区别？
        //方案三：重定向，且保持原本所在的页面需要附加pageNum和keyword两个属性
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    //新增
    @RequestMapping("/admin/do/add/page.html")
    public String addAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;//做了页面合理化修正，超出页面会到最后一页
    }

    //跳转到修改页面的查询
    //要先进行回显
    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId") Integer adminId,
                             ModelMap modeMap
                             ){
        Admin admin = adminService.getAdminById(adminId);
        modeMap.addAttribute("admin",admin);
        return "admin-edit";
    }

    //修改的实际操作
    @RequestMapping("admin/update.html")
    public String update(Admin admin,@RequestParam("pageNum") Integer pageNum,@RequestParam("keyword") String keyword ){
        //表单提交的数据是请求参数的一部分
        adminService.update(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }
}