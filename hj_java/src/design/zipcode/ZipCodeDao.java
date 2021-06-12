 package design.zipcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import util.DBConnection;

/* 인스턴스화
 * A a = new A();
 * A a = null;	a = new A();
 * A a = A.getInstance();
 */

public class ZipCodeDao {
	Connection con = null; // 물리적으로 떨어져 있는 db서버와 연결통로 만들기
	PreparedStatement pstmt = null; // 위에서 연결되면 쿼리문을 전달할 전령의 역할을 하는 인터페이스 객체 생성하기
	ResultSet rs = null; // 조회된 결과를 화면에 처리해야 하므로 오라클에 커서를 조작하기 위해 ResultSet추가
	DBConnection dbMgr = null;
	
	public ZipCodeDao() {
		dbMgr = DBConnection.getInstance();
		try {
			con = dbMgr.getConnection();
		} catch (Exception e) {
			System.out.println("오라클 서버 연결실패~ㅠㅠ");
		}
	}
// 콤보박스에 담길 시도,정보, 조회하기 구현
 /*********************************************************
  * select '전체' zdo from dual union all
  *	select distinct(zdo) from zipcode_t order by zdo asc;
  * @return > 전체 경기, 강원, 경북...	
  *********************************************************/
	public String[] getZdoList() {
		String zdos[] = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select '전체' zdo from dual union all ");
		sql.append("select distinct(zdo) from zipcode_t order by zdo asc");
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			Vector<String> v = new Vector<>();
			while(rs.next()) {
				String zdo = rs.getString("zdo");
				v.add(zdo);
			}
			
			for(String zdo1:v) {
				System.out.println(zdo1);
			}
			zdos = new String[v.size()];
			v.copyInto(zdos);
		} catch (SQLException se) {
			System.out.println("SQLException : " + se.toString());
		} catch (Exception e) {
			System.out.println("Exception : " + e.toString());
		} finally{ // 사용한 자원반납
		// 안하면 오라클에서 세션을 닫아버림, 닫을떈 열린것의 역순으로
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return zdos;
	}
	
	public ArrayList<ZipCodeVO> getZipCodeList(String dong){
		System.out.println("호출성공" + dong); 
		ArrayList<ZipCodeVO> zipcodelist = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select zipcode, address");
		sql.append(" from zipcode_t where dong like ?||'%'");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			ZipCodeVO zcVO = null;
			
			while(rs.next()) {
				zcVO = new ZipCodeVO();
				zcVO.setZipcode(rs.getInt("zipcode"));
				zcVO.setZdo(rs.getString("address"));
				zipcodelist.add(zcVO);
			}
		} catch (SQLException se) {
			System.out.println("SQLException" + sql.toString());
		} catch (Exception e) {
			System.out.println("Exception" + e.toString());
		}
		
		return zipcodelist;
	 }
	
	public static void main(String args[]) {
		ZipCodeDao zcd = new ZipCodeDao();
		zcd.getZdoList();
		zcd.getZipCodeList("가산동");
		ArrayList<ZipCodeVO> zipcodelist = new ArrayList<>();
		for(ZipCodeVO zcVO:zipcodelist) {
			System.out.println(zcVO.getAddress() + ", " + zcVO.getZipcode());
		}
	}
	
}