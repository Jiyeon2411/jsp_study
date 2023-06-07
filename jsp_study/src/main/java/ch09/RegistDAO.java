package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistDAO {
	Connection con = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Regist> getAll() {
		open();
		ArrayList<Regist> register = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from regist");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Regist p = new Regist();
				
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setAddress(rs.getString("address"));
				p.setGrade(rs.getString("grade"));
				p.setPhone(rs.getString("phone"));
				
				register.add(p);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return register;
		}
		
	public void insert(Regist p) {
		open();
			try {
				pstmt = con.prepareStatement("insert into regist values(?, ?, ?, ?, ?)");
			
				pstmt.setString(1, p.getId());
				pstmt.setString(2, p.getName());
				pstmt.setString(3, p.getAddress());
				pstmt.setString(4, p.getGrade());
				pstmt.setString(5, p.getPhone());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			
	}

				
}
 