package ch05_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.EmpVO;

/* 물리적으로 떨어져있는 서버에 접속하려고 함
 * 서버 컴의 오라클 제품에 접속하려면 해당 회사가 제공하는 드라이버 클래스가 있어야 함
 *   -> ojdbc6.jar를 주입 받을 수 있도록 등록함	*/

public class JdbcTest {
	static final String _DRIVER= "oracle.jdbc.driver.OracleDriver";
	static final String _URL = "jdbc:oracle:thin:@192.168.0.10:1521:orcl11";
	String _USER = "scott";
	String _PW = "tigger";
	
	Connection con = null; 
	PreparedStatement pstmt = null; // 24번 서베에 내가 작성한 select문을 전달해줄 객체 선언
	ResultSet rs = null; // 오라클의 커서를 조작하는 객체 선언

	public JdbcTest() {
		String sql = "select empno, ename, sal from emp";
		
		try {
			Class.forName(JdbcTest._DRIVER); // 오라클 드라이버 클래스 로딩하기
			
			con = DriverManager.getConnection(_URL, _USER, _PW);
			pstmt = con.prepareStatement(sql); // 오라클 서버에 select문 전달할 전령 객체 생성
			rs = pstmt.executeQuery();
			
			EmpVO eVO = null; // 오라클에 살고 있는 커서 조작 위해 자바가 제공하는 객체 생성
			rs.next();
			rs.next();
			while(rs.next()) {
				eVO = new EmpVO();
				eVO.setEmpno(rs.getInt("empno"));
				eVO.setEname(rs.getString("ename"));
				eVO.setSal(rs.getDouble("sal"));
			/*	int rempno = rs.getInt("empno");
				String rename = rs.getNString("ename");
				double sal = rs.getDouble("sal");	*/
				
				System.out.println(eVO.getEmpno() + ", " + eVO.getEname() + ", " + eVO.getSal());
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로딩 실패");
			return;
		} catch(SQLException se) {
		 // 좀 더 구체적인 예외처리 클래스 정보를 알 수 있음 ↘
			System.out.println("SQLException: " + se.getMessage());
			// 부적합한 섹별자 입니다
		}
		System.out.println("요기");
	}

	public static void main(String[] args) {
		JdbcTest jt = new JdbcTest(); // 생성자 호출도 동시에 일어남
	}

}
