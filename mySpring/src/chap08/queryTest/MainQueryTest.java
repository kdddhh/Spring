package chap08.queryTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainQueryTest {

	static private AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppContext.class);
	}
	
	public static void main(String[] args) {
		MemberCountBean memberCount = context.getBean("memberCount", MemberCountBean.class);
		
		System.out.println("전체 회원 수: " + memberCount.getMemberCount());
	}
}
