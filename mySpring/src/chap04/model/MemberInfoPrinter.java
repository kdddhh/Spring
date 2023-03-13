package chap04.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import chap04.exception.MemberNotFoundException;

public class MemberInfoPrinter {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	// private MemberPrinter memberPrinter; => 기본
	// private MemberPrinter memberPrinter1; => 빈 이름으로 식별하는 방법
	//private MemberPrinter printerQ; => 필드명은 한정자로 사용할 수 없다.
	// @Qualifier("printerQ") // => 한정자 사용
	// private MemberPrinter memberPrinter;
	
	// => 한정자를 이용한 부모 클래스(MemberPrinter) 빈 적용
	@Qualifier("printerP")
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
		
		// memberPrinter1.print(member); => 빈 이름으로 식별
		memberPrinter.print(member);

	}
}
