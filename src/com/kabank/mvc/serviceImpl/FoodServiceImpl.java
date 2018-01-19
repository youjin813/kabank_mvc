package com.kabank.mvc.serviceImpl;

import com.kabank.mvc.domain.FoodBean;
import com.kabank.mvc.service.FoodService;

public class FoodServiceImpl implements FoodService{
	public static FoodService getInstance() {
		return new FoodServiceImpl();
		}
	private FoodServiceImpl() {
	}
	
	@Override
	public FoodBean findFoodById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
