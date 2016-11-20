package controller.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.item.Item;
@SuppressWarnings("serial")
@WebServlet("/insertitem")
public class InsertItemView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession(true);
		Object loginName = session.getAttribute("loginName");
		if(loginName == null) return;
		resp.getWriter().write("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" + 
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" + 
			"<head>\n" + 
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" + 
			"<title></title>\n" + 
			"</head>\n" + 
			"\n" + 
			"<body>\n" + 
			"	<form method='post'>\n" + 
			"    	<p><input type='text' name='iName' placeholder='条目名' /></p>\n" + 
			"    	<p><input type='text' name='iInfo' placeholder='风险内容' /></p>\n" + 
			"    	<p><input type='text' name='possibility' placeholder='可能性' /></p>\n" + 
			"    	<p><input type='text' name='influence' placeholder='影响程度' /></p>\n" + 
			"    	<p><input type='text' name='trigger' placeholder='触发器/阈值' /></p>\n" + 
			"    	<p><input type='text' name='riskState' placeholder='风险状态' /></p>\n" + 
			"    	<p><input type='submit' value='创建' /></p>\n" + 
			"    </form>\n" + 
			"</body>\n" + 
			"</html>\n");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession(true);
		Object loginName = session.getAttribute("loginName");
		if(loginName == null) return;
		Item item = new Item();
		item.iName = req.getParameter("iName");
		item.iInfo = req.getParameter("iInfo");
		item.possibility = req.getParameter("possibility");
		item.influence = req.getParameter("influence");
		item.trigger = req.getParameter("trigger");
		item.submitUser = loginName.toString();
		item.riskState = req.getParameter("riskState");
		Item.insertItem(item);
		resp.getWriter().write("<script>window.close()</script>");
	}
}
