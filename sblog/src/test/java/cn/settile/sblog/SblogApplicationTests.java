package cn.settile.sblog;

import cn.settile.sblog.service.ThemeService;
import cn.settile.sblog.utils.CommonUtil;
import cn.settile.sblog.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SblogApplication.class)
@TestPropertySource(locations ="file:./sblog-dev/application-test.properties")
public class SblogApplicationTests {

	@Test
	public void contextLoads() {

	}

}
