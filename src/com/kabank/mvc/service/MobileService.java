package com.kabank.mvc.service;

import com.kabank.mvc.domain.MobileBean;

public interface MobileService {

	public void open();

	public MobileBean findMobileById(String id);
}
