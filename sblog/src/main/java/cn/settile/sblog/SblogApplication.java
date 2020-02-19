package cn.settile.sblog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.PrintStream;

/**
 * @author lzjyz
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@Slf4j
public class SblogApplication extends SpringBootServletInitializer implements CommandLineRunner {

	String test;
	public static void main(String[] args) {
		System.setProperty("spring.config.additional-location", "file:./sblog/,file:./sblog-dev/");
		SpringApplication springApplication = new SpringApplication(SblogApplication.class);
		springApplication.run(args);
	}
	@Override
	public void run(String... args) throws Exception {
		//TODO 自定义配置设置
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("spring.config.additional-location", "file:./sblog/,file:./sblog-dev/");
		return application.sources(SblogApplication.class);
	}
	/**
	 * RequestContextListener监听器
	 * @return
	 */
	@Bean
	public RequestContextListener requestContextListenerBean() {
		return new RequestContextListener();
	}
}
