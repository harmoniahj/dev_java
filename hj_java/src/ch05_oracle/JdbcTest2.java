package ch05_oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnectionMgr;
import vo.EmpVO;

/* 물리적으로 떨어져있는 서버에 접속하려고 함
 * 서버 컴의 오라클 제품에 접속하려면 해당 회사가 제공하는 드라이버 클래스가 있어야 함
 *   -> ojdbc6.jar를 주입 받을 수 있도록 등록함	*/

public class JdbcTest2 {
	Connection        con = null;
	PreparedStatement pstmt = null;
	ResultSet           rs      = null;
	DBConnectionMgr dbMgr = null;
	
	public JdbcTest2() {
		String sql = "SELECT empno, ename, sal FROM emp";
	    dbMgr = DBConnectionMgr.getInstance();
	    try {
	    	con =dbMgr.getConnection();
	    	pstmt = con.prepareStatement(sql);
	    	rs = pstmt.executeQuery();
	    	EmpVO eVO = null;
	    	
	    	while(rs.next()) {
	    		eVO = new EmpVO();
	            eVO.setEmpno(rs.getInt("empno"));
	            eVO.setEname(rs.getString("ename"));
	            eVO.setSal(rs.getDouble("sal"));
	            System.out.println(eVO.getEmpno()+", "+eVO.getEname()+", "+eVO.getSal());
	    	}
	    } catch (SQLException se) {
	    	System.out.println("SQLException:"+se.getMessage()); //좀 더 구체적인 예외처리 클래스 정보를 알 수 있다.
	    }
	    System.out.println("요기");
	}
	
	public static void main(String[] args) {
		JdbcTest2 jt = new JdbcTest2();//생성자 호출도 동시에 일어 남니다.
	}
}