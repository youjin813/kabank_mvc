package com.kabank.mvc.serviceImpl;

import com.kabank.mvc.daoImpl.BankDAOImpl;
import com.kabank.mvc.domain.AccountBean;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.service.BankService;

public class BankServiceImpl implements BankService{
	public static BankService getInstance() {
		return new BankServiceImpl();	
	}
	private BankServiceImpl() {
	}
	@Override
	public void create() {
		String account = String.valueOf((int)(Math.random()*8999+1000)+"-"
				+(int)(Math.random()*8999+1000)+"-"+(int)(Math.random()*8999+1000));
		BankDAOImpl.getInstance().createAccount(account);
	}

	@Override
	public AccountBean findAccountById(String id) {
		return BankDAOImpl.getInstance().selectBanKById(id);
	}

}
