package com.nttdata.emea.devschool.vehicleordering.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CurrentDateServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		String currentTime = Calendar.getInstance().getTime().toString();
		out.println("It's " + currentTime + "!");
	}
}
