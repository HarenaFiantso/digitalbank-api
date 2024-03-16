package com.digital.bank;

import com.digital.bank.util.database.DbProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.flywaydb.core.Flyway;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class DigitalbankApplication {

	@Autowired
	private DbProperties dbProperties;

	public static void main(String[] args) {
		SpringApplication.run(DigitalbankApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void migrateOnStartup() {
		Flyway flyway = Flyway.configure()
				.dataSource(
						dbProperties.getUrl(),
						dbProperties.getUser(),
						dbProperties.getPassword())
				.load();
		flyway.migrate();
	}
}
