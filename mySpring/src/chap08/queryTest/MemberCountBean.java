package chap08.queryTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberCountBean {
	
	@Autowired
	private DataSource ds;
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public MemberCountBean() {		
	}
	
	public int getMemberCount() {
		int rowCount = 0;
		String sql = "select count(*) as count from member";
		
		try {
			this.conn = ds.getConnection();
			this.stmt = conn.createStatement();
			this.rs = stmt.executeQuery(sql);
			
			if(this.rs.next()) {
				rowCount = rs.getInt("count");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!this.conn.isClosed()) {
					this.conn.close(); // conn을 반납하면 자동으로 stmt, ptmtm, rs 모두가 반납됨
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
}
