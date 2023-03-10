package chap03.model;

import java.time.LocalDateTime;

public class MemberRegisterService {
	//실제로 회원 등록 작업을 수행하는 클래스
	
	private MemberDAO memberDAO;
	
	public MemberRegisterService() {
	}
	
	public MemberRegisterService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	public long regist(RegisterRequest req) {
		Member member = memberDAO.selectByEmail(req.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException();
		}
		
		member = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDAO.insert(member);
		
		return member.getId();
	}
}
