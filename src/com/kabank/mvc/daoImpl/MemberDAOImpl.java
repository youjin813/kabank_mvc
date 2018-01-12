package com.kabank.mvc.daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kabank.mvc.command.InitCommand;
import com.kabank.mvc.dao.MemberDAO;
import com.kabank.mvc.domain.MemberBean;
import com.kabank.mvc.enums.DMLEnum;
import com.kabank.mvc.enums.ResultEnum;
import com.kabank.mvc.enums.MemberEnum;
import com.kabank.mvc.enums.OracleEnum;
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
	public List<MemberBean> selectMembers() {
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
		return result;
	}

	@Override
	public void insertMember(MemberBean a) {
		System.out.println("insert들어옴========");
		System.out.println("ID : " + a.getId());
		try {
			StringBuffer buffer = new StringBuffer(DMLEnum.INSERT.toString());
			buffer.insert(11, " "+TnameEnum.MEMBER.toString()+" ("+MemberEnum.PROPERTIES.toString()+")")
			.append(ResultEnum.PERCENT.getValue());
			System.out.println("버퍼====="+buffer);
			DatabaseFactory.create(Vendor.ORACLE).getConnection()
			.createStatement()
			.executeUpdate(String.format(buffer.toString(),
					a.getId(),a.getPass(),a.getName(),a.getSsn(),a.getPhone(),a.getEmail(),a.getProfile(),a.getAddr()));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public MemberBean selectMemberById(MemberBean m) {
		System.out.println("======selectMemberById()IN====================");
		MemberBean mem = null;
		try {
			StringBuffer buff = new StringBuffer(DMLEnum.SELECT.toString());
			buff.insert(6, " "+MemberEnum.PROPERTIES.toString()).append(TnameEnum.MEMBER.toString());
			buff.append("WHERE id = '%s' AND pass = '%s'");
			System.out.println("=============="+buff);
			ResultSet rs = DatabaseFactory.create(Vendor.ORACLE).getConnection()
			.createStatement()
			.executeQuery(
					String.format(
					buff.toString(), m.getId(), m.getPass()));
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
		System.out.println("=============:"+m.toString()+"==========");
		return mem;
	}
	@Override
	public MemberBean login() {
		System.out.println("===MEMBER-D: LOGIN IN=========");
		StringBuffer sql = new StringBuffer(
				MemberEnum.LOGIN.toString());
		String[] arr = InitCommand.cmd.getData().split("/");
		System.out.println("ID"+arr[0]);
		System.out.println("PASS"+arr[1]);
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
		System.out.println("===MEMBER-D: LOGIN OUT=========");
		return member;
	}
}