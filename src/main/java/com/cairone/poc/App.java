package com.cairone.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try (	Connection conn = dataSource.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM cities")) {

			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}
	}

	@Autowired
	private DataSource dataSource;
}
