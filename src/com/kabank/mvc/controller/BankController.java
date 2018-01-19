package com.kabank.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.command.MoveCommand;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.serviceImpl.BankServiceImpl;
import com.kabank.mvc.util.DispatcherServlet;

@WebServlet("/kakao.do")
public class BankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		InitCommand init = new InitCommand(request);
		init.execute();
		switch (InitCommand.cmd.getAction()) {
		case MOVE:
			System.out.println("=======BANK CONTROLLER :MOVE IN==========");
			move(request);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			DispatcherServlet.send(request, response);
			System.out.println("=======BANK CONTROLLER :MOVE OUT==========");
			break;
		case CREATE_ACCOUNT:
			System.out.println("=======BANK CONTROLLER :CREATE_ACCOUNT IN==========");
			System.out.println("세션 있는 멤버:"+((MemberBean) session.getAttribute("user")).toString());
			InitCommand.cmd.setData(((MemberBean)session.getAttribute("user")).getId());
			System.out.println("++++++++++"+InitCommand.cmd.getData());
			BankServiceImpl.getInstance().create();
			session.getAttribute(InitCommand.cmd.getData());
			new MoveCommand(request).execute();
			DispatcherServlet.send(request, response);
			System.out.println("=======BANK CONTROLLER :CREATE_ACCOUNT OUT==========");
			break;
		default:
			break;
		}
	}
	private void move(HttpServletRequest request) {
		new MoveCommand(request).execute();
		}
}
