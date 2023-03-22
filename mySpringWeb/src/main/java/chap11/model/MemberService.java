package chap11.model;

import java.util.ArrayList;
import java.util.Collection;

public class MemberService {
	
	private MemberDaoSpring memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = (MemberDaoSpring) memberDao;
	}
	
	public boolean checkLoginAuth(LoginCommand login) {
		boolean result = false;
		Member member = memberDao.selectByEmail(login.getEmail());
		
		if(member != null && member.checkPassword(login.getPassword())) {
			return true;
		}
		
		return result;
	}
	
	public Member findMember(String email) {
		return memberDao.selectByEmail(email);
	}
	
	public ArrayList<Member> allfindMember() {
		ArrayList<Member> list = (ArrayList<Member>)memberDao.selectAll();
		
		return list;
	}
}
