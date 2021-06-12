package ch05_oracle;

import java.sql.Connection;

import util.DBConnectionMgr;

public class DBConTest {

	public static void main(String[] args) {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Connection con = null;
		
		try {
			con = dbMgr.getConnection();
		} catch (Exception e) {
			System.out.println("Exception : " + e.toString());
		}
		System.out.println("con : " + con);
	}

}
