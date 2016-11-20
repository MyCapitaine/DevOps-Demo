package controller.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.db.DbController;

public class User {
	public String uName;
	
	public static boolean checkUser(String uName) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from users where uname='" + uName + "';";
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
}
