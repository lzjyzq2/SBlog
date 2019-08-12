package cn.settile.sblog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SblogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SblogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" ________  ________  ___       ________  ________     \n" +
				"|\\   ____\\|\\   __  \\|\\  \\     |\\   __  \\|\\   ____\\    \n" +
				"\\ \\  \\___|\\ \\  \\|\\ /\\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|    \n" +
				" \\ \\_____  \\ \\   __  \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___  \n" +
				"  \\|____|\\  \\ \\  \\|\\  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\ \n" +
				"    ____\\_\\  \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\\n" +
				"   |\\_________\\|_______|\\|_______|\\|_______|\\|_______|\n" +
				"   \\|_________|                                       \n" +
				"                                                      \n" +
				"                                                      ");
	}
}
