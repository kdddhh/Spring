package chap03.main;

import java.io.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap03.config.*;
import chap03.model.*;
import chap03.exception.*;

public class MainForSpring2 {
	
	private static AnnotationConfigApplicationContext context;
	
	static {
		context = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
	}
			
	
	private void printHelp() {
		System.out.println("\n 잘못된 명령입니다. 아래 사용법을 확인하세요.");
		System.out.println("\n ### 명령어 사용법 ###");
		System.out.println("\n 명령어를 입력하세요: new 이메일 이름 암호 암호확인");
		System.out.println("\n 명령어를 입력하세요: change 이메일 현재암호 변경암호");
		System.out.println("\n 명령어를 입력하세요: list");
		System.out.println("\n 명령어를 입력하세요: info 이메일");
		System.out.println("\n 명령어를 입력하세요: version");
		System.out.println("\n 명령어를 입력하세요: exit \n");
	}

	private void processNewCommand(String[] args) {
		if(args.length != 5) {
			this.printHelp();
			return;
		}
		
		RegisterRequest req = new RegisterRequest();
		MemberRegisterService regSvc = context.getBean("memberRegSvc", MemberRegisterService.class);
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println(" 암호와 암호 확인이 일치하지 않습니다. \n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println(" 회원 정보를 등록했습니다. \n");
		}
		catch(DuplicateMemberException e) {
			System.out.println(" 이미 존재하는 이메일입니다. \n");
		}
	}
	
	private void processChangeCommand(String[] args) {
		if(args.length != 4) {
			this.printHelp();
			return;
		}
		
		ChangePasswordService pwdSvc = context.getBean("changePwdSvc", ChangePasswordService.class);
		
		try {
			pwdSvc.changePassword(args[1], args[2], args[3]);
			System.out.println(" 비밀번호가 변경되었습니다.\n");
		}
		catch(MemberNotFoundException e) {
			System.out.println(" 존재하지 않는 이메일입니다.\n");
		}
		catch(WrongIdPasswordException e) {
			System.out.println(" 잘못된 아이디 또는 패스워드입니다.\n");
		}

	}
	
	private void processListCommand() {
		MemberListPrinter memberListPrinter = context.getBean("memberListPrint", MemberListPrinter.class);
		
		memberListPrinter.allPrint();
	}
	
	private void processInfoCommand(String[] args) {
		if(args.length != 2) {
			this.printHelp();
			return;
		}
		
		MemberInfoPrinter memberInfoPrinter = context.getBean("infoPrinter", MemberInfoPrinter.class);
		
		try {
			memberInfoPrinter.printByEmail(args[1]);
		}
		catch(MemberNotFoundException e) {
			System.out.println(" 존재하지 않는 이메일입니다.\n");
		}
	}
	
	private void processVersionCommand() {
		VersionPrinter versionPrinter = context.getBean("versionPrinter", VersionPrinter.class);
		
		versionPrinter.versionPrint();
	}
	
	public static void main(String[] args) throws IOException {
		// 스캐너보다 효율적으로 사용자에게 값을 입력받는 방법
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 키보드로부터 사용자 입력을 받음
		MainForSpring2 main = new MainForSpring2();
		String command = "";
		
		System.out.println("\n ### 회원 관리 프로그램 ### \n");
		
		while(true) {
			System.out.print("명령어를 입력하세요: ");
			command = reader.readLine();
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("\n 프로그램을 종료합니다.");
				break;
			}
			else if(command.startsWith("new ")) {
				main.processNewCommand(command.split(" ")); // split()는 구분을 수행하는 메소드 여기선 " " 공백을 구분자로 지정
				
			}
			else if(command.startsWith("change ")) {
				main.processChangeCommand(command.split(" "));
				
			}
			else if(command.startsWith("list")) {
				main.processListCommand();
			}
			else if(command.startsWith("info ")) {
				main.processInfoCommand(command.split(" "));
			}
			else if(command.startsWith("version")) {
				main.processVersionCommand();
			}
			else {
				main.printHelp();
			} // else블럭을 쓰지 않으려면 new와 change를 수행하는 if문에 continue를 넣어주면 됨 무한 반복이니깐 ㅇㅇ
		}

	}

}
