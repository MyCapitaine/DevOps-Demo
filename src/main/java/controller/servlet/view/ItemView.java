package controller.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.item.Item;
@SuppressWarnings("serial")
@WebServlet("/item/*")
public class ItemView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		
		String[] uris = req.getRequestURI().split("/");
		String iid = uris[uris.length - 1];
		Item item = Item.getItem(iid);
		if(item == null) return;
		resp.getWriter().write("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
			"<head>\n" +
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
			"<title></title>\n" +
			"</head>\n" +
			"\n" +
			"<body>\n" +
			"	<ul>\n" +
			"    	<li>\n" +
			"        	条目名：" + item.iName + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	风险内容：" + item.iInfo + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	可能性：" + item.possibility + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	影响程度：" + item.influence + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	触发器/阈值：" + item.trigger + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	提交者：" + item.submitUser + "\n" +
			"        </li>\n" +
			"    	<li>\n" +
			"        	风险状态：" + item.riskState + "\n" +
			"        </li>\n" +
			"	</ul>\n" +
			"\n" +
			"</body>\n" +
			"</html>\n");
		
	}
}
