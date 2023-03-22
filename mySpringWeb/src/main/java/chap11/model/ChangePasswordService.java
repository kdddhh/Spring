package chap11.model;

import org.springframework.transaction.annotation.Transactional;
import chap11.exception.*;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	public ChangePasswordService() {
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void changePassword(ChangePasswdCommand command) {
		Member member = memberDao.selectByEmail(command.getEmail());
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		if(command.getPassword().equals(command.getNewPassword())) {
			throw new WrongIdPasswordException();
		}
		
		member.changePassword(command.getPassword(), command.getNewPassword());
		memberDao.update(member);
	}
}
