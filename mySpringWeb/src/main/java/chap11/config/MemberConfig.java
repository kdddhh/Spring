package chap11.config;

import java.time.format.DateTimeFormatter;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import chap11.model.*;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2);
		ds.setMinIdle(3);
		ds.setMaxIdle(3);
		ds.setMaxActive(3);
		ds.setMinEvictableIdleTimeMillis(60000);
		ds.setTimeBetweenEvictionRunsMillis(5000);
		
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		
		return tm;
	}
	
	@Bean
	public MemberDaoSpring memberDao() {
		return new MemberDaoSpring(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(this.memberDao());
	}
	
	@Bean
	public MemberService memberSvc() {
		return new MemberService(this.memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(this.memberDao());
		
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(this.memberDao(), this.memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//		infoPrinter.setMemberDao(this.memberDao());
//		infoPrinter.setMemberPrinter(this.memberPrinter());
		
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		
		return versionPrinter;
	}
	
	@Bean
	public DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	
}
