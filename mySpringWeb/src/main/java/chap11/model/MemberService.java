package chap11.model;

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
}
