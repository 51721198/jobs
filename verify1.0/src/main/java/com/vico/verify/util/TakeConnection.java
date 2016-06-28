package com.vico.verify.util;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class TakeConnection {

	public static Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/license", "root", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return connection;
	}
}
