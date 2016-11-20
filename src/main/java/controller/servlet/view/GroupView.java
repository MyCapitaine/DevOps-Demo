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
@SuppressWarnings("serial")
@WebServlet("/group/*")
public class GroupView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		Object uName = req.getSession().getAttribute("loginName");
		if(uName == null) return;
		String[] uris = req.getRequestURI().split("/");
		String gid = uris[uris.length - 1];
		List<Item> inSideItems = Group.getGroupItems(gid);
		List<Item> outSideItems = Group.getOutsideGroupItems(gid);
		Group thisGroup = Group.getGroup(gid);
		if(thisGroup == null) return;
		
		String page = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
			"<head>\n" +
			"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
			"<title></title>\n" +
			"<script type='text/javascript'>\n" +
			"function setInfo(id,method) {\n" +
			"    document.getElementById('opId').value = id;\n" +
			"    document.getElementById('opMethod').value = method;\n"	+
			"}\n" +
			"</script>\n" +
			"</head>\n" +
			"\n" +
			"<body>\n" +
			"   <h1>" + thisGroup.gname + "</h1>\n" +
			"   <form method='post'>\n" +
			"   <input type='hidden' id='opId' name='opId'/>\n" +
			"   <input type='hidden' id='opMethod' name='opMethod'/>\n" +
			"	<ul>\n" +
			"    	<li>\n" +
			"            <div>\n" +
			"                <p>\n" +
			"                    <span>计划内风险条目</span> \n" +
			"                </p>\n" +            
			"                    <ul>\n";
			for(Item item : inSideItems) {
				page = page + 
						"                        <li>\n" +
						"                            <a href='../item/" + item.iid + "' target='_blank' >" + item.iName + "</a>\n" +
						"                            <input type='submit' value='移除' onclick=" + '"' + "setInfo('" + item.iid + "','delete');" + '"' + " />\n" +
						"                        </li>\n";
			}
			page = page +
			"                    </ul>\n" +
			"            </div>\n" +
			"            \n" +
			"        </li>\n" +
			"        <li>\n" +
			"            <div>\n" +
			"                <p>\n" +
			"                    <span>其他风险条目</span> \n" +
			"                </p>\n" +
			"                <ul>\n";
			for(Item item : outSideItems) {
				page = page + 
						"                        <li>\n" +
						"                            <a href='../item/" + item.iid + "' target='_blank' >" + item.iName + "</a>\n" +
						"                            <input type='submit' value='添加' onclick=" + '"' + "setInfo('" + item.iid + "','add');" + '"' + " />\n" +
						"                        </li>\n";
			}
			page = page +
			"                </ul>\n" +
			"            </div>\n" +
			"        </li>\n" +
			"    </ul>\n" +
			"    </form>\n" +
			"</body>\n" +
			"</html>\n";
		resp.getWriter().write(page);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String[] uris = req.getRequestURI().split("/");
		String gid = uris[uris.length - 1];
		String iid = req.getParameter("opId");
		String method = req.getParameter("opMethod");
		if(method.equals("delete"))
			Group.removeItemInGroup(iid, gid);
		else
			Group.insertItemInGroup(iid, gid);
		resp.sendRedirect(req.getRequestURI());
	}
	
}
