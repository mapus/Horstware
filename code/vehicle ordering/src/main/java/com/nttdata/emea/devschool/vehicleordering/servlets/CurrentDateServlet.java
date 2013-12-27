package com.nttdata.emea.devschool.vehicleordering.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentDateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setTextContentType(resp);
		
		writeCurrentDateResponse(resp);
	}

	private void setTextContentType(HttpServletResponse resp) {
		resp.setContentType("text/plain");
	}

	private void writeCurrentDateResponse(HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.write("It's ");
		out.write(new Date().toString());
	}

}
