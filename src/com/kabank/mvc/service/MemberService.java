package com.kabank.mvc.service;

import com.kabank.mvc.domain.MemberBean;

public interface MemberService {
	public MemberBean findMemberById(MemberBean member);
	public MemberBean login();

	public void join(MemberBean a);
}
