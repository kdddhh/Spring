package chap03.model;

public class RegisterRequest {
	// 내부적으로는 member를 사용하지만, 사용자랑 인터페이스할 때 사용하는 DO
	// id와 registerDateTime은 자동생성이기 때문에 사용자가 실제로 입력하는 값이 아니다.
	
	private String email;
	private String password;
	private String ConfirmPassword; // 입력한 비밀번호가 맞는지 한번 더 확인하는 필드
	private String name;
	
	public RegisterRequest() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isPasswordEqualToConfirmPassword() {
		return this.password.equals(this.ConfirmPassword);
	}
}
