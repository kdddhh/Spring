package chap03.assembler;

import chap03.model.*;

public class Assembler {
	private MemberDAO memberDAO;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDAO = new MemberDAO();
		regSvc = new MemberRegisterService(memberDAO);
		pwdSvc = new ChangePasswordService(); // ChangePasswordService는 생성자가 아닌 세터 메소드로 의존관계를 주입하기 때문에 별도로 세터를 호출해주자
		pwdSvc.setMemberDAO(memberDAO);
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public MemberRegisterService getRegSvc() {
		return regSvc;
	}

	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}
}
