package com.kabank.mvc.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.dao.BankDAO;
import com.kabank.mvc.domain.AccountBean;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.enums.DMLEnum;
import com.kabank.mvc.enums.MemberEnum;
import com.kabank.mvc.enums.OracleEnum;
import com.kabank.mvc.enums.Vendor;
import com.kabank.mvc.factory.DatabaseFactory;

public class BankDAOImpl implements BankDAO{
	
	public static BankDAO getInstance() {
		return new BankDAOImpl();
	}
	private  BankDAOImpl() {
		try {
			Class.forName(OracleEnum.ORACLE_DRIVER.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createAccount(String account) {
			System.out.println("===DAOIMPL: createAccount IN=========");
			String sql = DMLEnum.CREATEACCOUNT.toString();
			System.out.println("===DAOIMPL:========="+sql);
			System.out.println("멤버 서비스 임플 계좌 생성"+account);
			try {
				DatabaseFactory.create(Vendor.ORACLE)
						.getConnection()
						.createStatement()
						.executeUpdate(String.format(sql ,account, "100",InitCommand.cmd.getData()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		System.out.println("===DAOIMPL: createAccount OUT=========");
	}

	@Override
	public AccountBean selectBanKById(String id) {
		System.out.println("====SelectAccountById 입장====");
		StringBuffer sql = new StringBuffer(MemberEnum.SELECTACCOUNTBYID.toString());
		sql.replace(sql.indexOf("$"), sql.indexOf("$")+1,id);
		System.out.println("====SELECTACCOUNTBYID===="+id);
		System.out.println("SELECTACCOUNTBYID SQL문"+sql);
		AccountBean account = null;
		try {
			ResultSet rs = DatabaseFactory.create(Vendor.ORACLE)
			.getConnection()
			.createStatement()
			.executeQuery(sql.toString());
			while (rs.next()) {
				account = new AccountBean();
				account.setAccount_num(rs.getString(MemberEnum.ACCOUNT_NUM.toString()));
				account.setCustomer_num(rs.getString(MemberEnum.CUSTOMER_NUM.toString()));
				account.setMoney(rs.getString(MemberEnum.MONEY.toString()));
				System.out.println("계좌가 있는 회원 계좌 정보:"+account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}


}

