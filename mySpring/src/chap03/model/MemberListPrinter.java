package chap03.model;

import java.util.Collection;

public class MemberListPrinter {
private MemberDAO memberDAO;
private MemberPrinter printer;
	
	public MemberListPrinter(MemberDAO memberDAO, MemberPrinter printer) {
		this.memberDAO = memberDAO;
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
