package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	static Connection con;
	
	public static final String URL = "jdbc:mysql://localhost:3306/cozum_masasivt?verifyServerCertificate=false&useSSL=false&useUnicode=yes&characterEncoding=UTF-8";
	public static final String USER = "root";
	public static final String PASSWORD = "";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	public static Connection baglan() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}

		return con;
	}
	
	public static void closeconnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
