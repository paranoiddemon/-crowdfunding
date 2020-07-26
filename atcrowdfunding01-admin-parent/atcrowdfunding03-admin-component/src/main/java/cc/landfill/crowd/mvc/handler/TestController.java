package cc.landfill.crowd.mvc.handler;

import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.entity.Student;
import cc.landfill.crowd.service.api.AdminService;
import cc.landfill.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @title: TestController
 * @Author Landfill
 * @Date: 2020/7/24 10:50
 * @Version 1.0
 */

@Controller
public class TestController {
    private Logger logger =  LoggerFactory.getLogger(TestController.class);
    @Autowired
    private AdminService adminService;
    //测试SSM整合效果
    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        //模拟一个空指针异常
        //String a = null;
        //System.out.println(a.length());
        System.out.println(10/0);
        return "target";
    }
    //Ajax请求向服务器端发送数组
    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testReceive(@RequestParam("array[]") List<Integer> array){
        array.forEach(System.out::println);
        return "success";

    }
    @ResponseBody
    @RequestMapping("/send/array3.html")
    public String testReceive3(@RequestBody List<Integer> array){
        array.forEach(System.out::println);
        return "success";

    }
    //Ajax请求向服务器端发送复杂对象
    @ResponseBody
    @RequestMapping("/send/compose/object.html")
    public String testReceiveComposeObject(@RequestBody Student student){
        logger.info(student.toString());
        return "success";
    }
    //Ajax请求向服务器端发送复杂对象
    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject2(@RequestBody Student student){
        logger.info(student.toString());
        //模拟一个数学异常
        //System.out.println(10/0);
        return ResultEntity.successWithData(student);
        //该静态方法返回一个ResultEntity<Student> 该ResultEntity<Student>排队了数据student对象以及成功与否的信息
        //前端拿到的就是一个统一的对象  是这么理解吗？
    }
}