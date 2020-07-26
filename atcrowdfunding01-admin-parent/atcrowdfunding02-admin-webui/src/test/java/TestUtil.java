import cc.landfill.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @title: TestUtil
 * @Author Landfill
 * @Date: 2020/7/25 10:45
 * @Version 1.0
 */
public class TestUtil {

    @Test
    public void testMd5(){
        String pwdEncryption = CrowdUtil.pwdEncryption("123");

        System.out.println(pwdEncryption);
    }
}