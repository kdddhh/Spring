package chap11.model;

public class FindMemberService {
	
	private MemberDaoSpring memberDao;
	
	public FindMemberService(MemberDao memberDao) {
		this.memberDao = (MemberDaoSpring) memberDao;
	}
	
	public boolean checkMember(Member member) {
		boolean result = false;
		Member find = memberDao.selectByEmail(member.getEmail());
		
		if(find != null) {
			result = true;
		}
		
		return result;
	}
}
