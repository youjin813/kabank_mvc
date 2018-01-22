package com.kabank.mvc.serviceImpl;

import com.kabank.mvc.command.Command;
import com.kabank.mvc.command.ResultMap;
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
	public ResultMap create(Command cmd) {
		String account = String.valueOf((int)(Math.random()*8999+1000)+"-"
				+(int)(Math.random()*8999+1000)+"-"+(int)(Math.random()*8999+1000));
		return BankDAOImpl.getInstance().createAccount(cmd);
	}

	@Override
	public ResultMap findAccountById(Command cmd) {
		return BankDAOImpl.getInstance().selectBanKById(cmd);
	}

}
