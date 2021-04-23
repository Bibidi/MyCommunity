package com.bibidi.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	static {
		try {
			// 클래스 동적 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection con = DriverManager
				.getConnection(
						"jdbc:oracle:thin:@localhost:1521:XE",
						"oracle_ex",
						"oracle_ex")) {
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
