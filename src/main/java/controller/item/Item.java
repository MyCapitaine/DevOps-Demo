package controller.item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.db.DbController;

public class Item {
	public String iid;
	public String iName;
	public String iInfo;
	public String possibility;
	public String influence;
	public String trigger;
	public String submitUser;
	public String riskState;
	
	public static Item getItem(String iid) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from items where iid=" + iid + ";";
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				Item item = new Item();
				item.iid = result.getString("iid");
				item.iName = result.getString("iname");
				item.iInfo = result.getString("iInfo");
				item.possibility = result.getString("possibility");
				item.influence = result.getString("influence");
				item.trigger = result.getString("tri");
				item.submitUser = result.getString("submitUser");
				item.riskState = result.getString("riskState");
				return item;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public static boolean insertItem(Item item) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "insert into items values("
					+ item.iName + ","
					+ item.iInfo + ","
					+ item.possibility + ","
					+ item.influence + ","
					+ item.trigger + ","
					+ item.submitUser + "," 
					+ item.riskState + ");";
			return stmt.execute(sql);
		} catch (SQLException e) {
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public static boolean updateItem(Item item) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "update items set "
					+ "iname='" + item.iName + "',"
					+ "iInfo='" + item.iInfo + "',"
					+ "possibility='" + item.possibility + "',"
					+ "influence='" + item.influence + "',"
					+ "trigger='" + item.trigger + "',"
					+ "submitUser='" + item.submitUser + "'," 
					+ "riskState='" + item.riskState + "' where iid=" + item.iid + ";";
			return stmt.execute(sql);
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
