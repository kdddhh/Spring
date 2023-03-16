package chap08.model;

import org.springframework.transaction.annotation.Transactional;

import chap08.exception.MemberNotFoundException;
import chap08.exception.WrongIdPasswordException;

public class ChangePasswordService {
	private MemberDAO memberDAO;
	
	public ChangePasswordService() {
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Transactional
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = memberDAO.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		if(oldPassword.equals(newPassword)) {
			throw new WrongIdPasswordException();
		}
		
		member.changePassword(oldPassword, newPassword);
		memberDAO.update(member);
	}
}
