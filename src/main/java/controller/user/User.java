package controller.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.db.DbController;
import controller.item.Item;

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
	
	public static List<Item> getUserItems(String uName) {
		Connection con = DbController.getConnection();
		List<Item> items = new ArrayList<Item>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from items where submitUser='" + uName + "';";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Item item = new Item();
				item.iid = result.getString("iid");
				item.iName = result.getString("iname");
				item.iInfo = result.getString("iInfo");
				item.possibility = result.getString("possibility");
				item.influence = result.getString("influence");
				item.trigger = result.getString("tri");
				item.submitUser = result.getString("submitUser");
				item.riskState = result.getString("riskState");
				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			return items;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	
}
