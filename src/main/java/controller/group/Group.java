package controller.group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.db.DbController;
import controller.item.Item;

public class Group {
	public String gid;
	public String gname;
	
	public static Group getGroup(String gid) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from groups where gid=" + gid + ";";
			ResultSet result = stmt.executeQuery(sql);
			if(result.next()) {
				Group group = new Group();
				group.gid = gid;
				group.gname = result.getString("gname");
				return group;
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
	
	
	public static List<Group> getGroups() {
		Connection con = DbController.getConnection();
		List<Group> groups = new ArrayList<Group>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from groups;";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Group group = new Group();
				group.gid = result.getString("gid").toString();
				group.gname = result.getString("gname").toString();
				groups.add(group);
			}
			return groups;
		} catch (SQLException e) {
			return groups;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public static boolean insertGroup(Group group) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "insert into groups values(null,'" + group.gname + "');";
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
	
	public static List<Item> getGroupItems(String gid) {
		Connection con = DbController.getConnection();
		List<Item> items = new ArrayList<Item>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select iid,iname from items where iid in (select iid from itemwithgroup where gid=" + gid + ")";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Item item = new Item();
				item.iid = result.getString("iid").toString();
				item.iName = result.getString("iName").toString();
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
	
	public static List<Item> getOutsideGroupItems(String gid) {
		Connection con = DbController.getConnection();
		List<Item> items = new ArrayList<Item>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select iid,iname from items where iid not in (select iid from itemwithgroup where gid=" + gid + ")";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				Item item = new Item();
				item.iid = result.getString("iid").toString();
				item.iName = result.getString("iName").toString();
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
	
	public static boolean insertItemInGroup(String iid, String gid) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "insert into itemwithgroup values(" + gid + "," + iid + ");";
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
	
	public static boolean removeItemInGroup(String iid, String gid) {
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "delete from itemwithgroup where gid=" + gid + " and iid=" + iid + ";";
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
