package cc.landfill.crowd.service.api;

import cc.landfill.crowd.entity.Admin;

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
}