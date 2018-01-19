package com.kabank.mvc.service;

import com.kabank.mvc.domain.AccountBean;

public interface BankService {

	public void create();

	public AccountBean findAccountById(String id);

}
