package chap04.model;

public class MemberPrinter {
	
	public MemberPrinter() {
	}
	
	public void print(Member member) {
		System.out.printf("회원 정보: 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF \n", 
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
	//%tF 데이트 객체나 로컬 데이트 타임 객체를 값으로 받아서 yyyy-mm-dd 형식으로 출력
}
