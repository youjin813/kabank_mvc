package com.kabank.mvc.enums;

public enum DDLEnum {
	
		CREATE_TABLE_MEMBER{
			@Override
			public String toString() {
				return "CREATE TABLE Member(" 
						+"id VARCHAR2(20) PRIMARY KEY,"
						+"pass VARCHAR2(20),"
						+"name VARCHAR2(20),"
						+"ssn VARCHAR2(20),"
						+"phone VARCHAR2(20),"
						+"email VARCHAR2(20),"
						+"profile VARCHAR2(20),"
						+"addr VARCHAR2(20)"
						+")";
				
			}
		},
		CREATE_TABLE_ATTEND{
			@Override
			public String toString() {
				return "CREATE TABLE Attend("
					+"seq NUMBER PRIMARY KEY,"
					+"id VARCHAR2(20),"
					+"week VARCHAR2(20),"
					+"status VARCHAR2(20)"
					+")";
			}
		}
	}

