package controller.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.item.Item;
@SuppressWarnings("serial")
@WebServlet("/updateitem/*")
public class UpdateItemView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String[] uris = req.getRequestURI().split("/");
		String iid = uris[uris.length - 1];
		Item item = Item.getItem(iid);
		resp.getWriter().write("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
			"<head>\n" +
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
			"<title></title>\n" +
			"</head>\n" +
			"\n" +
			"<body>\n" +
			"	<form method='post'>\n" +
			"	<ul>\n" +
			"    	<p>条目名：<input type='text' name='iName' value='" + item.iName + "'/></p>\n" +
			"    	<p>风险内容：<input type='text' name='iInfo' value='" + item.iInfo + "'/></p>\n" +
			"    	<p>可能性：<input type='text' name='possibility' value='" + item.possibility + "'/></p>\n" +
			"    	<p>影响程度：<input type='text' name='influence' value='" + item.influence + "'/></p>\n" +
			"    	<p>触发器/阈值：<input type='text' name='trigger' value='" + item.trigger + "'/></p>\n" +
			"    	<p>风险状态：<input type='text' name='riskState' value='" + item.riskState + "'/></p>\n" +
			"    	<p><input type='submit' value='提交更改' /></p>\n" +
			"	</ul>\n" +
			"    </form>\n" +
			"</body>\n" +
			"</html>\n");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		Item item = new Item();
		String[] uris = req.getRequestURI().split("/");
		item.iid = uris[uris.length - 1];
		item.iName = req.getParameter("iName");
		item.iInfo = req.getParameter("iInfo");
		item.possibility = req.getParameter("possibility");
		item.influence = req.getParameter("influence");
		item.trigger = req.getParameter("trigger");
		item.riskState = req.getParameter("riskState");
		Item.updateItem(item);
		resp.getWriter().write("<script>window.close()</script>");
	}
}
