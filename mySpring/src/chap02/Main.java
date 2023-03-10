package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// 스프링을 설정하는 파일은 클래스, XML 등이 될 수 있다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
		// 위의 객체를 통해서 스프링 컨테이너를 만들고, 괄호 안의 설정 클래스로 만들겠다.
		Greeter g = context.getBean("greeter", Greeter.class); // 컨테이너에 등록된 빈의 이름이 메소드이름이 된다. 또한 클래스파일로 빈의 데이터 타입을 지정할 수 있다.
		
		String msg = g.greet("스프링");
		System.out.println(msg);
		
		Greeter g1 = context.getBean("greeter", Greeter.class);
		Greeter g2 = context.getBean("greeter", Greeter.class);
		
		System.out.println("g1 == g2 : " + (g1 == g2)); // 레퍼런스의 주소 값이 같은지 판별
		
		context.close();
		// 모든 작업이 끝나면 스프링 컨테이너를 닫아줘야한다.
	}

}
