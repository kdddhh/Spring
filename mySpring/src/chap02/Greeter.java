package chap02;

public class Greeter {
	private String format;
	
	public Greeter() {
	} // 빈즈로 사용되기 때문에 디폴트 생성자를 만든다.
	
	public String greet(String guest) {
		return String.format(this.format, guest);
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
