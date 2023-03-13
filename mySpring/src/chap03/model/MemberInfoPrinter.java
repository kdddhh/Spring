package chap03.model;

import chap03.exception.MemberNotFoundException;

public class MemberInfoPrinter {
	private MemberDAO memberDAO;
	private MemberPrinter printer;
		
	public MemberInfoPrinter() {
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	public void printByEmail(String email) {
		Member member = memberDAO.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		printer.print(member);
	}
}
