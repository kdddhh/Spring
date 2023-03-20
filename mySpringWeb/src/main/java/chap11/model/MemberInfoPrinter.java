package chap11.model;

import org.springframework.beans.factory.annotation.Autowired;

import chap11.exception.MemberNotFoundException;

public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;
	
	public MemberInfoPrinter() {
	}
	
	/*
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void setMemberPrinter(MemberPrinter printer) {
		this.memberPrinter = printer;
	}
	*/
	
	public void printMemberInfo(String email) {
		Member member = this.memberDao.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		this.memberPrinter.print(member);
		System.out.println();
	}
}
