package cc.landfill.crowd.service.api;

import cc.landfill.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @title: AdminService
 * @Author Landfill
 * @Date: 2020/7/23 23:03
 * @Version 1.0
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct,String userPswd);

    //根据关键词返回Admin列表
    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);
}
