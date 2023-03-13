package chap04.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {
	
	@Autowired	
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberPrinter printer;
	
	
	public MemberListPrinter() {
	}
	
	public void allPrint() {
		Collection<Member> members = memberDAO.selectAll();
		
		members.forEach(m -> printer.print(m)); // 람다식은 -> 앞에 매개변수, 뒤에는 매개변수를 사용할 함수식을 적어준다.
		
		/*for(Member member : members) {
			this.printer.print(member);
		} */
	}
}
