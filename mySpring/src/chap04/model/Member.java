package chap04.model;

import java.time.LocalDateTime;

import chap04.exception.WrongIdPasswordException;

public class Member {
	private long id; // 멤버 객체가 생성될때마다 자동생성 시킬거임
	private String email; // 이메일을 고유
	private String password;
	private String name;
	private LocalDateTime registerDateTime;
	
	public Member() {
	}
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!this.password.equals(oldPassword)) {
			throw new WrongIdPasswordException();
		}
		
		this.password = newPassword;
	}
}
