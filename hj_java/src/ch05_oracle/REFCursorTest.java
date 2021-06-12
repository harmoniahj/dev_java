package ch05_oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBConnection;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

// 싱글톤으로 사용
/*******************************************************************************
 * 대량의 정보를 조회시 오라클에서 제공하는 refcursor를 활용할 수 있음
 * create or replace procedure hju.proc_temp_list(p_temp out sys_refcursor)
 * is
 * begin
 *  open p_temp
 *  for select emp_id, emp_name, lev from temp;
 * end; 
 * 객체풀링 적용 이유 > 매번 동시 접속자 수가 많을 수 있고 다중처리를 해야 하므로 DB커넥션 풀링을 고려해야 함
 *******************************************************************************/

public class REFCursorTest {
	Connection con 	= null;
	CallableStatement cstmt	= null;
	OracleCallableStatement ocstmt 	= null;
	ResultSet rs = null;
	DBConnection dbMgr = null;
	
	public void init() {
		dbMgr = DBConnection.getInstance();//#3 싱글톤
	}
	
	public List<Map<String,Object>> getTempList(){
		List<Map<String,Object>> list = new ArrayList<>();
		
		try {
			con = dbMgr.getConnection();
			cstmt = con.prepareCall("{call proc_temp_list(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;
			rs = ocstmt.getCursor(1);
			Map<String,Object> rmap = null;
			
			while(rs.next()) {
				rmap = new HashMap<>();
				rmap.put("emp_id",rs.getInt("emp_id"));
				rmap.put("emp_name",rs.getString("emp_name"));
				rmap.put("lev",rs.getString("lev"));
				list.add(rmap);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}
	
	public static void main(String[] args) {
		REFCursorTest rt = new REFCursorTest();
		rt.init();
		List<Map<String,Object>> list = rt.getTempList();
		for(Map<String,Object> pMap:list) {
			System.out.println(pMap);
		}
	}
}