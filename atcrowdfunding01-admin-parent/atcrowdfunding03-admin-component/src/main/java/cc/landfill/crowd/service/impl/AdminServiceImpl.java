package cc.landfill.crowd.service.impl;

import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.entity.AdminExample;
import cc.landfill.crowd.mapper.AdminMapper;
import cc.landfill.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @title: AdminServiceImpl
 * @Author Landfill
 * @Date: 2020/7/23 23:03
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void saveAdmin(Admin admin) {
       adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}