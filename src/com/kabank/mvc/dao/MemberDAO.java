package com.kabank.mvc.dao;

import java.util.List;

import com.kabank.mvc.domain.MemberBean;

public interface MemberDAO {
	public List<MemberBean> selectMembers();

	public void insertMember(MemberBean a);

	public MemberBean selectMemberById(MemberBean m);

	public MemberBean login();



}
