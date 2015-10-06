package com.wencheng.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadAction() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		//System.out.println(repository.getAbsolutePath());
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		JSONObject jo = new JSONObject();
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			if(items.size()>0){
				FileItem item = items.get(0);
				if(!item.getName().endsWith(".dtd")){
					throw new Exception("");
				}
				InputStream in = item.getInputStream();
				File f = new File(getServletContext().getRealPath("/file/"+new SimpleDateFormat("YYYYMMdd").format(new Date())+"/"));
				if(!f.exists()){
					f.mkdir();
				}
				File file = new File(f.getAbsolutePath()+"/"+item.getName());
				if(file.exists()){
					file.delete();
				}
				FileOutputStream out = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = in.read(buffer))>0){
					//System.out.println(len);
					out.write(buffer,0,len);
				}
				out.close();
				in.close();
				jo.put("success", true);
				jo.put("file",item.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jo.put("success", false);
		}
		response.getWriter().println(jo);
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
