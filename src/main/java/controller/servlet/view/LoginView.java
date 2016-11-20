package controller.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.user.User;
@SuppressWarnings("serial")
@WebServlet("/")
public class LoginView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		Object uname = null;
		if((uname = req.getSession().getAttribute("loginName")) != null) {
			resp.sendRedirect("/DevOpsDemo/user/" + uname);
		}
		else {
			resp.getWriter().write("<!DOCTYPE HTML>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <meta charset='utf-8'>\n" +
				"    <title>风险管理系统</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <div>\n" +
				"        <h1>风险管理系统</h1>\n" +
				"        <p>(用check用户进行登录)</p>\n" +
				"    </div>\n" +
				"    <form method='POST'>\n" +
				"        <input placeholder='Username' type='text' name='loginName' />\n" +
				"        <input type='submit' value='log in' />\n" +
				"    </form>\n" +
				"</body>\n");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginName = req.getParameter("loginName") == null ? null : req.getParameter("loginName").toString();
		if(User.checkUser(loginName)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("loginName", loginName);
			session.setMaxInactiveInterval(3*24*60*60);
		}
		resp.sendRedirect(req.getRequestURI());
	}
}
