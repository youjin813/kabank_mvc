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
	public void join(MemberBean a) {
	System.out.println(a.getId());
		MemberDAOImpl.getInstance().insertMember(a);
	}

	@Override
	public MemberBean login() {
		return MemberDAOImpl.getInstance().login();
	}
}
