package chap04.model;

import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	
	private DateTimeFormatter dateTimeFormmater;
	
	public MemberPrinter() {
	}
	
	public void print(Member member) {
		if(dateTimeFormmater == null) {
			System.out.printf("회원 정보: 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF \n", 
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
			//%tF 데이트 객체나 로컬 데이트 타임 객체를 값으로 받아서 yyyy-mm-dd 형식으로 출력
		}
		else {
			System.out.printf("회원 정보: 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %s \n", 
					member.getId(), member.getEmail(), member.getName(), 
					dateTimeFormmater.format(member.getRegisterDateTime()));
		}
	}
	
	/*
	@Autowired(required = false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormmater = dateTimeFormatter;
		// required = false로 지정되어 있지 않으면, @Autowired는 필수적으로 실행된다. 
		// 현재는 DateTimeFormatter의 빈이 존재하지 않는다. 얘는 사용될 수 없다. 따라서 이대로 실행한다면 예외가 발생한다.
		// 따라서 required = false로 설정하여 빈이 존재하면 실행하고, 없으면 실행하지 않는 형태로 만들어준다.
		// 다시, 지금은 AppContext 클래스에 DateTimeFormatter 빈을 만들고 출력형식을 지정해두었다.
		// 이제는 더 이상 DateTimeFormatter 빈이 널이 아니다.
	}
	*/
	
	@Autowired
	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormmater = dateTimeFormatter;
		
		// 지금 required = false가 없어서 필수적으로 실행되어야 하지만, @Nullable으로 선택적인 처리로 바뀐다.
		// 이 둘의 기능은 같지만, 동작방식이 다르다.
		// required = false는 주입할 빈이 있으면 실행되고 없으면 실행되지 않는다.
		// @Nullable은 주입할 빈이 있거나 없거나 무조건 실행된다. 없을때는 매개변수의 값이 자동으로 널이 된다.
		// required = false, @Nullable 모두 필드에도 똑같이 적용된다.
	}
	
}
