package com.wencheng.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wencheng.util.FileParseDtd;

public class Show extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Show() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file = request.getParameter("filename");
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		if(width == null){
			width = "250";
		}
		if(height == null){
			height = "100";
		}
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		FileParseDtd f = new FileParseDtd(getServletContext().getRealPath("/file/"+new SimpleDateFormat("YYYYMMdd").format(new Date())+"/"+file));
		JSONObject json = f.getBigJson("first_item_256", "起始");
		request.setAttribute("json", json.toString());
		request.getRequestDispatcher("/WEB-INF/views/show.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
