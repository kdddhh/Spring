package chap04.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {
	
		
	private MemberDAO memberDAO;	
	private MemberPrinter printer;
	
	
	public MemberListPrinter() {
	}
	
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Autowired // 세터메소드에 사용하고 있기 때문에 매개변수의 이름과 타입을 설정 클래스의 빈즈명, 타입과 동일하게 선언한다.
	// public void setPrinter(MemberPrinter memberPrinter2) {  => 빈 이름으로 식별하는 방법
	// public void setPrinter(MemberPrinter printerQ) { => 한정자를 메소드의 매개변수 명으로 사용할 수 없다.
	/*
	@Qualifier("printerQ") // => 한정자 사용
	public void setPrinter(MemberPrinter printer) {	
		this.printer = printer;
	}
	*/
	
	// => 한정자를 이용한 자식 클래스(MemberSummaryPrinter) 빈 적용
	@Qualifier("printerC")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	

	public void allPrint() {
		Collection<Member> members = memberDAO.selectAll();
		
		members.forEach(m -> printer.print(m)); // 람다식은 -> 앞에 매개변수, 뒤에는 매개변수를 사용할 함수식을 적어준다.
		
		/*for(Member member : members) {
			this.printer.print(member);
		} */
	}
}
