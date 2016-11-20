package controller.servlet.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.group.Group;
import controller.item.Item;
import controller.user.User;
@SuppressWarnings("serial")
@WebServlet("/user/*")
public class UserView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String[] uris = req.getRequestURI().split("/");
		String uname = uris[uris.length - 1];
		if(!req.getSession().getAttribute("loginName").equals(uname)) return;
		
		List<Item> allItems = Item.getItems();
		List<Item> userItems = User.getUserItems(uname);
		List<Group> allGroups = Group.getGroups();
		
		String page = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
			"<head>\n" +
			"    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
			"    <title></title>\n" +
			"</head>\n" +
			"\n" +
			"<body>\n" +
			"	<div>\n" +
			"    	<h1>你好," + uname + "</h1>\n" +
			"    	<a href='../logout'>logout</a>\n" +
			"    </div>\n" +
			"    <ul>\n" +
			"    	<li>\n" +
			"            <div>\n" +
			"                <p>\n" +
			"                    <span>全部风险条目</span> \n" +
			"                    <a href='../insertitem' target='_blank'>发布风险条目</a>\n" +
			"                </p>\n" +
			"                <ul>\n";
			for(Item item : allItems) {
				page += "                    <li><a href='../item/" + item.iid + "' target='_blank'>" + item.iName + "</a></li>\n";
			}
			page = page +
			"                </ul>\n" +
			"            </div>\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"            <div>\n" +
			"                <p>\n" +
			"                    <span>我的风险条目</span> \n" +
			"                </p>\n" +
			"                <ul>\n";
			for(Item item : userItems) {
				page = page + "                    <li>\n" +
							  "                        <a href='../item/" + item.iid + "' target='_blank'>" + item.iName + "</a>\n" +
							  "                        <a href='../updateitem/" + item.iid + "' target='_blank'>修改</a>\n" +
							  "                    </li>\n";
			}
			page = page +
			"                </ul>\n" +
			"            </div>\n" +
			"        </li>\n" +
			"        <li>\n" +
			"        	<div>\n" +
			"        		<p>全部风险管理计划</p>\n" +
			"                <ul>\n";
			for(Group group : allGroups) {
				page = page + "                    <li><a href='../group/" + group.gid + "' target='_blank'>" + group.gname + "</a></li>\n";
			}
			page = page +
			"                </ul>\n" +
			"            </div>\n" +
			"        </li>\n" +
			"        <li>\n" +
			"        	<div>\n" +
			"        		<p>构建风险管理计划</p>\n" +
			"                <form method='post'>\n" +
			"                	<input type='text' name='gname' placeholder='风险管理计划名' />\n" +
			"                    <input type='submit' value='构建' />\n" +
			"                </form>\n" +
			"            </div>\n" +
			"        </li>\n" +
			"        <li>\n" +
			"        	<div>\n" +
			"        		<p>风险库统计(演变成问题最多)</p>\n" +
//			"              	<form>\n" +
			"                	<input type='date'/>\n" +
			"                    -\n" +
			"                    <input type='date'/>\n" +
			"                    <a href='../chart/1'>统计</a>\n" +
//			"                </form>\n" +
			"            </div>\n" +
			"        </li>    \n" +
			"        <li>\n" +
			"        	<div>\n" +
			"        		<p>风险库统计(识别最多)</p>\n" +
//			"              	<form>\n" +
			"                	<input type='date'/>\n" +
			"                    -\n" +
			"                    <input type='date'/>\n" +
			"                    <a href='../chart/2'>统计</a>\n" +
//			"                </form>\n" +
			"            </div>\n" +
			"        </li>     \n" +
			"    </ul>\n" +
			"</body>\n" +
			"</html>\n";
		resp.getWriter().write(page);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		
		Group group = new Group();
		group.gname = req.getParameter("gname");
		Group.insertGroup(group);
		resp.sendRedirect("/DevOpsDemo/");
	}
}
