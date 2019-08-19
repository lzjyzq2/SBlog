package cn.settile.sblog;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class SblogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SblogApplication.class);
		springApplication.setBanner(new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println(" ________  ________  ___       ________  ________     \n" +
						"|\\   ____\\|\\   __  \\|\\  \\     |\\   __  \\|\\   ____\\    \n" +
						"\\ \\  \\___|\\ \\  \\|\\ /\\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|    \n" +
						" \\ \\_____  \\ \\   __  \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___  \n" +
						"  \\|____|\\  \\ \\  \\|\\  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\ \n" +
						"    ____\\_\\  \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\\n" +
						"   |\\_________\\|_______|\\|_______|\\|_______|\\|_______|\n" +
						"   \\|_________|                                       \n" +
						"                                                      ");
			}
		});
		springApplication.run(args);
	}
	@Override
	public void run(String... args) throws Exception {
		//TODO 自定义配置设置
	}
}
