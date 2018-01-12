package com.kabank.mvc.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kabank.mvc.command.InitCommand;

public class DispatcherServlet {
	public static void send(HttpServletRequest request,
					HttpServletResponse response) 
					throws ServletException, IOException{
		request
		.getRequestDispatcher(InitCommand.cmd.getView())
		.forward(request, response);
	}
}
