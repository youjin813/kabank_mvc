package com.kabank.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kabank.mvc.command.Command;
import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.command.MoveCommand;
import com.kabank.mvc.command.LoginCommand;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.enums.Action;

import com.kabank.mvc.factory.ActionFactory;
import com.kabank.mvc.service.MemberService;
import com.kabank.mvc.serviceImpl.MemberServiceImpl;
import com.kabank.mvc.util.DispatcherServlet;

@WebServlet("/user.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		InitCommand init = new InitCommand(request);
		init.execute();
		switch (InitCommand.cmd.getAction()) {
		case MOVE:
			System.out.println("=======MEMBER :MOVE==========");
			move(request);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			DispatcherServlet.send(request, response);
			break;
		case LOGIN:
			System.out.println("=======MEMBER-C :LOGIN IN==========");
			login(request,session);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			System.out.println("=======MEMBER_C :LOGIN OUT==========");
			DispatcherServlet.send(request, response);
			break;
		case CHANGE:
			System.out.println("=======MEMBER :CHANGE==========");
			InitCommand.cmd.setPage("user");
			InitCommand.cmd.setPage("change_pass");
			break;
		case ADD:
			System.out.println("=======MEMBER :ADD==========");
			System.out.println("=========진입");
			MemberBean a = new MemberBean();
			a.setId(request.getParameter("id"));
			a.setPass(request.getParameter("pass"));
			a.setName(request.getParameter("name"));
			a.setSsn(request.getParameter("ssn"));
			a.setPhone(request.getParameter("phone"));
			a.setEmail(request.getParameter("email"));
			a.setProfile(request.getParameter("profile"));
			a.setAddr(request.getParameter("addr"));
			System.out.println("ID : "+a.getId());
			break;
		}
		
	}

	private void login(HttpServletRequest request,HttpSession session) {
		new LoginCommand(request).execute();
		MemberBean member = MemberServiceImpl.getInstance().login();
		System.out.println("EMAIL:"+ member.getEmail());
		if(MemberServiceImpl.getInstance().login()==null) {
			InitCommand.cmd.setDir("user");
			InitCommand.cmd.setPage("login");
		}else {
			session.setAttribute("user", member);
			InitCommand.cmd.setPage("bitcamp");
			InitCommand.cmd.setPage("main");
		}
		move(request);
	}

	private void move(HttpServletRequest request) {
		new MoveCommand(request).execute();
	}

}