package controller.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbController {
	private static final String dataSourceUrl = "java:comp/env/jdbc/devopsdemo";
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context context = new InitialContext();
			DataSource ods = (DataSource) context.lookup(dataSourceUrl);
			con = ods.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
