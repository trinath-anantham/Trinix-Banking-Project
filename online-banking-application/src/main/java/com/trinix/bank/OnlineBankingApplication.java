package com.trinix.bank;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.trinix.bank.config.ApplicationConfig;

@SpringBootApplication
@EnableScheduling
public class OnlineBankingApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF).sources(ApplicationConfig.class).build().run(args);
	}
}
