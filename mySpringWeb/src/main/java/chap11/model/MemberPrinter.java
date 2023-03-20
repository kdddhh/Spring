package chap11.model;

public class MemberPrinter {

	public void print(Member member) {
		System.out.printf(" 회원 정보: 아이디=%d\t이메일=%s\t이름=%s\t등록일=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
}
