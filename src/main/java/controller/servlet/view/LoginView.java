package controller.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.user.User;
import controller.util.encrypt.Encryptor;
@WebServlet("/")
public class LoginView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object uname = null;
		if((uname = req.getSession().getAttribute("loginName")) != null) {
			resp.sendRedirect("/user/" + uname);
		}
		else {
			
			
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginName = req.getAttribute("loginName") == null ? null : req.getAttribute("loginName").toString();
		if(User.checkUser(loginName)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("loginName", loginName);
			session.setMaxInactiveInterval(3*24*60*60);
		}
		else {
			resp.sendRedirect("/");
		}
		
	}
}
