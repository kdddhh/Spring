package chap04.model;

import org.springframework.beans.factory.annotation.Autowired;

import chap04.exception.MemberNotFoundException;

public class MemberInfoPrinter {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberPrinter memberPrinter;
		
	public MemberInfoPrinter() {
	}
	
	/*
	 * public void setMemberDAO(MemberDAO memberDAO) { this.memberDAO = memberDAO; }
	 * 
	 * public void setPrinter(MemberPrinter printer) { this.memberPrinter = printer;
	 * }
	 */
	
	public void printByEmail(String email) {
		Member member = this.memberDAO.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		memberPrinter.print(member);
	}
}
