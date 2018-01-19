package com.kabank.mvc.dao;

import com.kabank.mvc.domain.AccountBean;

public interface BankDAO {
	public void createAccount(String r);
	
	public AccountBean selectBanKById(String id);	
}
