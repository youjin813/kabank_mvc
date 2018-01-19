package com.kabank.mvc.serviceImpl;


import com.kabank.mvc.daoImpl.MemberDAOImpl;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.service.MemberService;

public class MemberServiceImpl implements MemberService{
	public static MemberService getInstance() {
		return new MemberServiceImpl();
	}

	@Override
	public MemberBean findMemberById(MemberBean m) {
		return MemberDAOImpl.getInstance().selectMemberById(m);
	}

	@Override
	public void join() {
		MemberDAOImpl.getInstance().joinMember();
	}

	@Override
	public MemberBean login() {
		return MemberDAOImpl.getInstance().login();
	}

	@Override
	public void changePass(MemberBean member) {
		MemberDAOImpl.getInstance().updatePass(member);
	}

	@Override
	public void delete() {
		MemberDAOImpl.getInstance().deleteMember();
	}
}
