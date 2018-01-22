package com.kabank.mvc.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kabank.mvc.command.Command;
import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.command.ResultMap;
import com.kabank.mvc.dao.MemberDAO;
import com.kabank.mvc.domain.AccountBean;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.enums.DMLEnum;
import com.kabank.mvc.enums.MemberEnum;
import com.kabank.mvc.enums.OracleEnum;
import com.kabank.mvc.enums.ResultEnum;
import com.kabank.mvc.enums.TnameEnum;
import com.kabank.mvc.enums.Vendor;
import com.kabank.mvc.factory.DatabaseFactory;


public class MemberDAOImpl implements MemberDAO{

	public static MemberDAO getInstance() {
		return new MemberDAOImpl();
	}
	private MemberDAOImpl() {
		try {
			Class.forName(OracleEnum.ORACLE_DRIVER.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ResultMap selectMembers(Command cmd) {
		List<MemberBean> result = new ArrayList<>();  
	try {
		StringBuffer buff = new StringBuffer(DMLEnum.SELECT.toString());
		buff.insert(6, " "+MemberEnum.PROPERTIES.toString()).append(TnameEnum.MEMBER.toString());
		ResultSet rs = DatabaseFactory.create(Vendor.ORACLE).getConnection()
		.createStatement()
		.executeQuery(buff.toString());
		while(rs.next()) {	
				MemberBean m = new MemberBean();
				m.setId(rs.getString(ResultEnum.ID.getValue())); 
				m.setPass(rs.getString(ResultEnum.PASS.getValue())); 
				result.add(m); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMap selectMemberById(Command cmd) {
		System.out.println("======selectMemberById()IN====================");
		MemberBean mem = null;
		try {
			StringBuffer buff = new StringBuffer(DMLEnum.SELECT.toString());
			buff.insert(6," "+MemberEnum.PROPERTIES.toString()).append(TnameEnum.MEMBER.toString()).append(" WHERE id = '%s' AND pass = '%s'");
			System.out.println("=============="+buff);
			ResultSet rs = DatabaseFactory.create(Vendor.ORACLE).getConnection()
			.createStatement()
			.executeQuery(
					String.format(
					buff.toString(), "m.getId()", "m.getPass()"));
			while(rs.next()) {
				mem = new MemberBean();
				mem.setId(rs.getString(ResultEnum.ID.getValue()));
				mem.setPass(rs.getString(ResultEnum.PASS.getValue()));	
				mem.setName(rs.getString(ResultEnum.NAME.getValue()));	
				mem.setSsn(rs.getString(ResultEnum.SSN.getValue()));	
				mem.setPhone(rs.getString(ResultEnum.PHONE.getValue()));	
				mem.setEmail(rs.getString(ResultEnum.EMAIL.getValue()));	
				mem.setProfile(rs.getString(ResultEnum.PROFILE.getValue()));	
				mem.setAddr(rs.getString(ResultEnum.ADDR.getValue()));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("=============:"+m.toString()+"==========");
		return null;
	}
	@Override
	public ResultMap login(Command cmd) {
		System.out.println("===MEMBER-D: LOGIN IN=========");
		StringBuffer sql = new StringBuffer(MemberEnum.LOGIN.toString());
		String[] arr = InitCommand.cmd.getData().split("/");
		System.out.println("=====MEMBERDAOIMPL LOGIN DATA"+InitCommand.cmd.getData());
		System.out.println("ID :"+arr[0]);
		System.out.println("PASS :"+arr[1]);
		sql.replace(sql.indexOf("$"), sql.indexOf("$")+1, arr[0]);
		sql.replace(sql.indexOf("@"), sql.indexOf("@")+1, arr[1]);
		System.out.println("::::SQL::::"+sql.toString());
		MemberBean member = null;
		try {
			ResultSet rs = DatabaseFactory.create(Vendor.ORACLE)
					.getConnection()
					.createStatement()
					.executeQuery(sql.toString());
			while(rs.next()) {
				member = new MemberBean();
				member.setId(rs.getString(MemberEnum.ID.toString()));
				member.setPass(rs.getString(MemberEnum.PASS.toString()));
				member.setName(rs.getString(MemberEnum.NAME.toString()));
				member.setEmail(rs.getString(MemberEnum.EMAIL.toString()));
				member.setAddr(rs.getString(MemberEnum.EMAIL.toString()));
				member.setPhone(rs.getString(MemberEnum.PHONE.toString()));
				member.setSsn(rs.getString(MemberEnum.SSN.toString()));
				member.setProfile(rs.getString(MemberEnum.PROFILE.toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("===MEMBER-D: LOGIN OUT=========\n"+member.toString());
		return null;
	}

	@Override
	public ResultMap updatePass(Command cmd) {
			System.out.println("====MEMBER-D: updatePass====");
			MemberBean member = null;
			System.out.println("memberupdatePass member = "+ member.getPass());
			System.out.println("memberupdatePass :"+InitCommand.cmd.getData());
			try {
				StringBuffer sql = new StringBuffer(DMLEnum.UPDATEPASS.toString());
				sql.replace(sql.indexOf("$"), sql.indexOf("$")+1,member.getId());
				sql.replace(sql.indexOf("@"), sql.indexOf("@")+1, member.getPass());
				System.out.println("======================"+member.getId());
				System.out.println("======================"+member.getPass());
				ResultSet rs = DatabaseFactory.create(Vendor.ORACLE).
						getConnection().
						createStatement().
						executeQuery(sql.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("====MEMBER-D: updatePass OUT====");
		return null;
	}
	
	@Override
	public ResultMap deleteMember(Command cmd) {
		System.out.println("===MEMBER-D: delete IN=========");
		StringBuffer sql = new StringBuffer(DMLEnum.DELETE.toString()); 
		sql.replace(sql.indexOf("@"), sql.indexOf("@")+1, InitCommand.cmd.getData());
		System.out.println("================="+InitCommand.cmd.getData());
		System.out.println(sql+"------------------------");
		try {
			DatabaseFactory.create(Vendor.ORACLE)
			.getConnection()
			.createStatement()
			.executeQuery(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ResultMap joinMember(Command cmd) {
		return null;		
	}
}

//	@Override
//	public void joinMember() {
//		System.out.println("=====MEMBER D JOIN IN======");
//		String sql;
//		try {
//			DatabaseFactory.create(Vendor.ORACLE)
//			.getConnection()
//			.createStatement()
//			.executeUpdate(sql.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("=====MEMBER D JOIN OUT======");
//			}
//		}

