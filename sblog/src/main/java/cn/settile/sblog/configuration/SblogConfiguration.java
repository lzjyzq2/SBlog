package cn.settile.sblog.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzjyz
 * @date 2019-8-14
 */
@Configuration
@Data
public class SblogConfiguration {
    //TODO SBlog的自定义配置实现

    @Value("${spring.mail.username}")
    String mailName;
    @Value("${sblog.web.url}")
    String webUrl;
    @Value("${sblog.web.name}")
    String webName;
}
