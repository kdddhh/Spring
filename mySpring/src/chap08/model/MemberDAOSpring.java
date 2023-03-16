package chap08.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import chap08.exception.MemberNotFoundException;


public class MemberDAOSpring extends MemberDAO {

   private JdbcTemplate jdbcTemplate;
   
   private String sql;
   

   public MemberDAOSpring(DataSource ds) {
	   this.jdbcTemplate = new JdbcTemplate(ds);
   }

   /* 1. RowMapper 인터페이스를 익명클래스로 구현하는 방법
   @Override
   public Member selectByEmail(String email) {
      this.sql = "select * from member where email = ?";

      List<Member> results = jdbcTemplate.query(this.sql,
    		  new RowMapper<Member>() {
    	  @Override
    	  public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    		  Member member = new Member();
    		  member.setId(rs.getLong("id"));
    		  member.setEmail(rs.getString("email"));
    		  member.setPassword(rs.getString("password"));
    		  member.setName(rs.getString("name"));
    		  member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
    		  
    		  return member;
    	  } 
      }, email); 
     
      return results.isEmpty() ? null : results.get(0);
   }
	*/

   /* 2. RowMapper 인터페이스를 람다 표현식(Lambda Expression)로 구현하는 방법
   @Override
   public Member selectByEmail(String email) {
      this.sql = "select * from member where email = ?";

      List<Member> results = jdbcTemplate.query(this.sql,
    	 (ResultSet rs, int rowNum) -> {
    		  Member member = new Member();
    		  member.setId(rs.getLong("id"));
    		  member.setEmail(rs.getString("email"));
    		  member.setPassword(rs.getString("password"));
    		  member.setName(rs.getString("name"));
    		  member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
    		  
    		  return member;
      }, email); 
     
      return results.isEmpty() ? null : results.get(0);
   }
   */
   
   // 3번 RowMapper 인터페이스를 직접 구현하는 MemberRowMapper 클래스를 생성하여 구현하는 방법
   // 무조건 데이터를 하나 반환할 수 있어야하기 때문에 데이터가 없어도 예외가 발생한다.
   // 따라서 다음과 같이 예외처리를 해야한다.
   @Override
   public Member selectByEmail(String email) {
      this.sql = "select * from member where email = ?";
      Member member = null;
      
      try {
    	  member = jdbcTemplate.queryForObject(this.sql, new MemberRowMapper(), email); 
      }
      catch(EmptyResultDataAccessException e) {
    	  // throw new MemberNotFoundException();
      }
     
      return member;
   }
   
   /*
    * 1번(임의 클래스), 2번(람다)의 방법 둘 다 재사용이 불가능하다는 단점이 있다.
    * 매번 필요할 때마다 반복해서 호출해야 한다.
    * 따라서 3번 방법(명시적으로 직접 인터페이스 구현)이 필요하다.
    */
   
   /*
   @Override
   public void insert(Member member) {
      this.sql = "insert into member (id, email, password, name) values (member_id_seq.nextval, ?, ?, ?)";
      
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "id" });
			// new String[]은 읽어야하는 특정 컬럼명을 지정해주는 역할이다. 이는 하나일 수도, 여러 개일수도 있기 때문에 배열로 선언한다.
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			
			return pstmt;
		}
	}, keyHolder);
     
      Number keyValue = keyHolder.getKey();
      member.setId(keyValue.longValue());
   }
   */
   
   /* KeyHolder를 사용하는 경우
   @Override
   public void insert(Member member) {
	   jdbcTemplate.update(new MemberPreparedStatementCreator(member));
   }
   */
   
   // 사용하지 않는 경우
   @Override
   public void insert(Member member) {
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   jdbcTemplate.update(new MemberPreparedStatementCreator(member, new String[] {"id"}), keyHolder);
	   
	   Number keyValue = keyHolder.getKey();
	   member.setId(keyValue.longValue());
   }
 
   @Override
   public void update(Member member) {
      this.sql = "update member set password = ? where email = ?";

      jdbcTemplate.update(this.sql, member.getPassword(), member.getEmail());
      
   }

   /* 임의 클래스 방식
   @Override
   public Collection<Member> selectAll() {
      this.sql = "select * from member order by id";
      List<Member> results = jdbcTemplate.query(this.sql,
    		  new RowMapper<Member>() {
    	  @Override
    	  public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    		  Member member = new Member();
    		  member.setId(rs.getLong("id"));
    		  member.setEmail(rs.getString("email"));
    		  member.setPassword(rs.getString("password"));
    		  member.setName(rs.getString("name"));
    		  member.setRegisterDateTime(rs.getTimestamp("regdate").toLocalDateTime());
    		  
    		  return member;
    	  } 
      }); 
     
      return results;
   }
   */
   
   @Override
   public Collection<Member> selectAll() {
      this.sql = "select * from member order by id";
      List<Member> results = jdbcTemplate.query(this.sql, new MemberRowMapper()); 
     
      return results;
   }
}