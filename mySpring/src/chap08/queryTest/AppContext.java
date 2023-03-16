package chap08.queryTest;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2); //커넥션 풀 초기화 시 생성할 초기 커넥션 갯수 기본 10개
		ds.setMinIdle(2); // 유지할 수 있는 최소 갯수, 기본값 maxActive
		ds.setMaxActive(5); // 사용할 수 있는 최대 갯수, 기본값 100
		// setMaxIdle 유지할 수 있는 최대 갯수, 기본값 maxActive
		// setMinEvictableIdleTimeMillis 유후 상태로 유지할 최소 시간을 밀리초 단위로 지정, 기본 값 60,000(1분)
		// setTimeBetweenEvictionRunsMillis 유후 커넥션을 검사할 주기를 밀리초 단위로 지정 5,000(5초)
		return ds;
	}
	
	@Bean
	public MemberCountBean memberCount() {
		return new MemberCountBean();
	}
}
