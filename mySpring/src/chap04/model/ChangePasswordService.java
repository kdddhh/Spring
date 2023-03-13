package chap04.model;

import org.springframework.beans.factory.annotation.Autowired;
import chap04.exception.*;

public class ChangePasswordService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public ChangePasswordService() {
	}
	
	
	/*
	 * @Autowired 
	 * public void setMemberDAO(MemberDAO memberDAO) { 
	 * 
	 * 	this.memberDAO = memberDAO; 
	 * 
	 * }
	 */
	 
	
	
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member member = this.memberDAO.selectByEmail(email);
		
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
