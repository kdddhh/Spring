package chap04.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {
	
		
	private MemberDAO memberDAO;	
	private MemberPrinter printer;
	
	
	public MemberListPrinter() {
	}
	
	@Autowired
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Autowired
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
