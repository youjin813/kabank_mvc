package com.kabank.mvc.enums;

public enum MemberEnum {
	ID,PASS,NAME,SSN,PHONE,EMAIL,ADDR,PROFILE,ACCOUNT_NUM,CUSTOMER_NUM,MONEY,
	PROPERTIES{
		@Override
		public String toString() {
			return ID+","+PASS+","+NAME+","+SSN+","+PHONE+","+EMAIL+","+ADDR+","+PROFILE;
		}
	},
	LOGIN{
		@Override
		public String toString() {
			return "SELECT ID, pass, name, ssn, phone, addr, email, profile "
					+ "FROM Member WHERE id LIKE '$' AND pass LIKE '@'";
		}
	},
	SELECTACCOUNTBYID{
		@Override
		public String toString() {
			return "SELECT customer_num, account_num, money"
					+" FROM bank WHERE and id LIKE '$'";
		}
	},
	
}
