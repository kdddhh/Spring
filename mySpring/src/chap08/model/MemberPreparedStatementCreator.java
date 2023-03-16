package chap08.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class MemberPreparedStatementCreator implements PreparedStatementCreator {

	private Member member;
	private String[] keyColumns;
	
	public MemberPreparedStatementCreator(Member member) {
		this(member, new String[] {});
	} // KeyHolder 사용하지 않을 때 이용하는 생성자
	
	public MemberPreparedStatementCreator(Member member, String[] keyColumns) {
		this.member = member;
		this.keyColumns = keyColumns;
	} // KeyHolder 사용할 때 이용하는 생성자

	@Override
	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into member (id, email, password, name) values (member_id_seq.nextval, ?, ?, ?)";
		
		if(keyColumns.length > 0) {
			pstmt = con.prepareStatement(sql, keyColumns);
		}
		else {
			pstmt = con.prepareStatement(sql);
		}
		
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		
		return pstmt;
	}
}
