package com.kabank.mvc.serviceImpl;

import com.kabank.mvc.daoImpl.MobileDAOImpl;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.domain.MobileBean;
import com.kabank.mvc.service.MobileService;

public class MobileServiceImpl implements MobileService{
	public static MobileService getInstance() {
		return new MobileServiceImpl();	
	}

	@Override
	public void open() {
		String number = "010"+"-"+String.valueOf((int)(Math.random()*8999+1000))+"-"
				+(int)(Math.random()*8999+1000);
		System.out.println("----생성된 전화 번호---"+number);
		MobileDAOImpl.getInstance().openNumber(number);
	}

	@Override
	public MobileBean findMobileById(String id) {
		return null;
	}

	
}
