import cc.landfill.crowd.entity.Admin;
import cc.landfill.crowd.mapper.AdminMapper;
import cc.landfill.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @title: TestCrowd
 * @Author Landfill
 * @Date: 2020/7/23 16:17
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)  //整合Junit和Spring，可以在测试的时候直接注入
@ContextConfiguration(locations={"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})  //指定Spring的配置文件的位置
public class TestCrowd {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;
    /*已经由sqlSessionFactory创建了，不再需要
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.getMapper(UserMapper.class);  可以直接使用mapper了，都有Spring来管理了*/

    @Autowired
    private AdminService adminService;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }

    @Test
    public void testInsert() throws SQLException {
        Admin admin = new Admin(null, "tom", "123", "汤姆", "tom@qq.com", null);
        int insert = adminMapper.insert(admin); //受影响的行数
        System.out.println(insert);
    }

    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(TestCrowd.class);
        logger.debug("debug--------------");
        logger.debug("info--------------");
        logger.debug("warn--------------");
        logger.debug("error--------------");
    }

    @Test
    public void testTx(){
        Admin admin = new Admin(null, "jerry", "123", "杰瑞", "jerry@qq.com", null);

        adminService.saveAdmin(admin);
    }
}