package cc.landfill.crowd.service.impl;

import cc.landfill.crowd.constant.CrowdConstant;
import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.entity.AdminExample;
import cc.landfill.crowd.exception.LoginAcctAlreadyInUserException;
import cc.landfill.crowd.exception.LoginAcctAlreadyInUserForUpateException;
import cc.landfill.crowd.exception.LoginFailedException;
import cc.landfill.crowd.mapper.AdminMapper;
import cc.landfill.crowd.service.api.AdminService;
import cc.landfill.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @title: AdminServiceImpl
 * @Author Landfill
 * @Date: 2020/7/23 23:03
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private AdminMapper adminMapper;
    //新增admin
    @Override
    public void saveAdmin(Admin admin) {
        //密码加密
        String userPswd = admin.getUserPswd();
        String pwdEncrypted = CrowdUtil.pwdEncryption(userPswd);
        admin.setUserPswd(pwdEncrypted);
        //生成创建时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(date);
        admin.setCreateTime(createTime);

        //重复的时候要抛出异常
        try{
        adminMapper.insert(admin);

        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUserException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }

        }

    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        //使用mybatis-generator中的方法。不用写sql语句。也可以自己去写select
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        //查到一个Admin的list 其实正常只有一个元素
        List<Admin> adminList = adminMapper.selectByExample(adminExample);

        //查询不存在，抛出异常
        if(adminList == null || adminList.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //查询超出1个，数据库中有多个用户对象。
        if (adminList.size() > 1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        //获取admin并判断是否为空
        Admin admin = adminList.get(0);
        if(admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 如果不为空。就拿出密码校验。这个用户是拿用户名查出来的。现在要查询核验密码
        // 取出数据库查询的admin的密码
        String userPswdDB = admin.getUserPswd();
        // 加密用户传入的明文密码。比对数据
        String userPswdForm = CrowdUtil.pwdEncryption(userPswd);
        // 使用Obejects.Equals 已经做了非空的判断
        if(!Objects.equals(userPswdDB,userPswdForm)){
            // 密码不一致，抛出登录失败异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
            // 抛异常就不再执行后面的代码，就不用return了
        }
        // 所有判断都成功，返回admin对象给controller
        return admin;
    }

    //根据关键词查询admin
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //非侵入式设计
        //查询分页的列表
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        PageInfo<Admin> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    //通过id删除admin
    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);

    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    //执行更新操作
    @Override
    public void update(Admin admin) {
        try{
        adminMapper.updateByPrimaryKeySelective(admin); //更新修改部分，null值不更新

        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUserForUpateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }

        }
    }


}