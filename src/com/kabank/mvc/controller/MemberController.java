package com.kabank.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kabank.mvc.command.ChangeCommand;
import com.kabank.mvc.command.Command;
import com.kabank.mvc.command.DeleteCommand;
import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.command.JoinCommand;
import com.kabank.mvc.command.MoveCommand;
import com.kabank.mvc.command.LoginCommand;
import com.kabank.mvc.domain.AccountBean;
import com.kabank.mvc.domain.FoodBean;
import com.kabank.mvc.domain.LottoBean;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.domain.MobileBean;
import com.kabank.mvc.enums.Action;

import com.kabank.mvc.factory.ActionFactory;
import com.kabank.mvc.service.MemberService;
import com.kabank.mvc.serviceImpl.BankServiceImpl;
import com.kabank.mvc.serviceImpl.FoodServiceImpl;
import com.kabank.mvc.serviceImpl.LottoServiceImpl;
import com.kabank.mvc.serviceImpl.MemberServiceImpl;
import com.kabank.mvc.serviceImpl.MobileServiceImpl;
import com.kabank.mvc.util.DispatcherServlet;
import com.sun.org.apache.bcel.internal.generic.NEW;

@WebServlet("/user.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		InitCommand init = new InitCommand(request);
		init.execute();
		MemberBean member =null;
		switch (InitCommand.cmd.getAction()) {
		case MOVE:
			System.out.println("=======MEMBER :MOVE IN==========");
			move(request);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			DispatcherServlet.send(request, response);
			System.out.println("=======MEMBER :MOVE OUT==========");
			break;
		case LOGIN:
			System.out.println("=======MEMBER-C :LOGIN IN==========");
			login(request,session);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			System.out.println("=======MEMBER_C :LOGIN OUT==========");
			DispatcherServlet.send(request, response);
			break;
		case LOGOUT:
			System.out.println("=======MEMBER-C :LOGOUT IN==========");
			session.invalidate();
			move(request);
			DispatcherServlet.send(request, response);
			System.out.println("=======MEMBER-C :LOGOUT OUT==========");
			break;
		case MYPAGE:
			mypage(request);
			break;	
		case CHANGE_PASS:
			System.out.println("=======MEMBER :CHANGE IN==========");
			member = new MemberBean();
			new ChangeCommand(request).execute();
			System.out.println("수정할비번"+InitCommand.cmd.getData());
			String id = ((MemberBean) session.getAttribute("user")).getId();
			member.setId(id);
			member.setPass(InitCommand.cmd.getData());
			MemberServiceImpl.getInstance().changePass(member);	
			member=(MemberBean) session.getAttribute("user");
			session.setAttribute("user", member);
			System.out.println("변경확인");
			System.out.println(member.toString());
			move(request);
			System.out.println("DEST IS"+InitCommand.cmd.getView());
			System.out.println("=======MEMBER :CHANGE OUT==========");
			DispatcherServlet.send(request, response);
			break;
		case DELETE:
			System.out.println("=======MEMBER :DELETE IN==========");
			new DeleteCommand(request).execute();
			System.out.println(InitCommand.cmd.getData()+"11111111111111111111");
			MemberServiceImpl.getInstance().delete();
			session.invalidate();
			move(request);
			DispatcherServlet.send(request, response);
			System.out.println("=======MEMBER :DELETE out==========");
			break;
		case JOIN:
			System.out.println("==============MEMBER-C : JOIN IN===========");
			new JoinCommand(request).execute();
			MemberServiceImpl.getInstance().join();
			new MoveCommand(request).execute();
			DispatcherServlet.send(request, response);
			System.out.println("==============MEMBER-C : JOIN OUT===========");
			break;
		default:
			break;
		}	
	}

	private void login(HttpServletRequest request,HttpSession session) {
		new LoginCommand(request).execute();
		MemberBean member = MemberServiceImpl.getInstance().login();
		if(member==null) {
			InitCommand.cmd.setDir("user");
			InitCommand.cmd.setPage("login");
		}else {
			session.setAttribute("user", member);
			mypage(request);
			InitCommand.cmd.setPage("bitcamp");
			InitCommand.cmd.setPage("main");
			System.out.println("방금 로그인 한 멤버:"+((MemberBean) session.getAttribute("user")).toString());
		}
		move(request);
	}

	private void move(HttpServletRequest request) {
		new MoveCommand(request).execute();
	}
	
	private void mypage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("user");
		//기본값은 무조건 있을것이다 그래서 널 체크가 필요없다 하지만 추가되는 객체 account phone 는 널일수 있다
		AccountBean a = BankServiceImpl.getInstance().findAccountById(member.getId());
		MobileBean m = MobileServiceImpl.getInstance().findMobileById(member.getId());
		LottoBean l = LottoServiceImpl.getInstance().findLottoById(member.getId());
		FoodBean f = FoodServiceImpl.getInstance().findFoodById(member.getId());
		if(a!=null) {member.setAccount(a);}
		if(m!=null) {member.setMobile(m);}
		if(l!=null) {member.setLotto(l);}
		if(f!=null) {member.setFood(f);}
		session.setAttribute("user", member);
		}
}
