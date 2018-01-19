package com.kabank.mvc.dao;

import com.kabank.mvc.domain.MemberBean;

public interface MobileDAO {

	public void openNumber(String number);

	public MemberBean SelectMobileById(String id);

}
