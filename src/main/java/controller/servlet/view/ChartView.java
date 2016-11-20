package controller.servlet.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.db.DbController;
@SuppressWarnings("serial")
@WebServlet("/chart/*")
public class ChartView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String[] uris = req.getRequestURI().split("/");
		String sign = uris[uris.length - 1];
		String condition = null;
		String title = null;
		if(sign.equals("1")) {
			condition = "data1";
			title = "演变成问题";
		}
		else if(sign.equals("2")) {
			condition = "data2";
			title = "被识别次数";
		}
		else return;
		
		List<String[]> datas = new ArrayList<String[]>();
		
		Connection con = DbController.getConnection();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from items;";
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				String[] data = new String[2];
				data[0] = result.getString("iname");
				data[1] = result.getString(condition);
				datas.add(data);
			}
		} catch (SQLException e) {
			return;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		String page = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n" +
			"<html xmlns='http://www.w3.org/1999/xhtml'>\n" +
			"<head>\n" +
			"<meta charset='UTF-8'>\n" +
			"<title></title>\n" +
			"<script src='http://www.ichartjs.com/ichart.latest.min.js'></script>\n" +
			"<script type='text/javascript'>\n" +
			"$(function(){\n" +
			"      var chart = iChart.create({\n" +
			"            render:'ichart-render',\n" +
			"            width:800,\n" +
			"            height:400,\n" +
			"            background_color:'#2e3b4e',\n" +
			"            gradient:false,\n" +
			"            color_factor:0.2,\n" +
			"            border:{\n" +
			"                  color:'#404c5d',\n" +
			"                  width:1\n" +
			"            },\n" +
			"            align:'center',\n" +
			"            offsetx:0,\n" +
			"            offsety:-20,\n" +
			"            sub_option:{\n" +
			"                  border:{\n" +
			"                        color:'#fefefe',\n" +
			"                        width:1\n" +
			"                  },\n" +
			"                  label:{\n" +
			"                        fontweight:600,\n" +
			"                        fontsize:20,\n" +
			"                        color:'#f5f5f5',\n" +
			"                        sign:'square',\n" +
			"                        sign_size:12,\n" +
			"                        border:{\n" +
			"                              color:'#BCBCBC',\n" +
			"                              width:1\n" +
			"                        },\n" +
			"                        background_color:'#fefefe'\n" +
			"                  }\n" +
			"            },\n" +
			"            shadow:true,\n" +
			"            shadow_color:'#fafafa',\n" +
			"            shadow_blur:10,\n" +
			"            showpercent:false,\n" +
			"            column_width:'70%',\n" +
			"            bar_height:'70%',\n" +
			"            radius:'90%',\n" +
			"            title:{\n" +
			"                  text:'" + title + "',\n" +
			"                  color:'#f5f5f5',\n" +
			"                  fontsize:24,\n" +
			"                  font:'Verdana',\n" +
			"                  textAlign:'left',\n" +
			"                  height:30,\n" +
			"                  offsetx:36,\n" +
			"                  offsety:0\n" +
			"            },\n" +
			"            type:'column2d',\n" +
			"            data:[\n";
			for(String[] data : datas) {
				page = page +   "			 {\n" +
								"                  name:'" + data[0] + "',\n" +
								"                  value:" + data[1] + ",\n" +
								"                  color:'gray'\n" +
								"            },\n";
			}
			page = page +
			"            ]\n" +
			"      });\n" +
			"      chart.draw();\n" +
			"});\n" +
			"</script>\n" +
			"</head>\n" +
			"<body style='background-color:#244c74;'>\n" +
			"<div id='ichart-render'></div>\n" +
			"</body>\n" +
			"</html>\n";
		
		resp.getWriter().write(page);
	}
}
