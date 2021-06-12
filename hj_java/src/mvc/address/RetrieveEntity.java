package mvc.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import util.DBConnectionMgr;

public class RetrieveEntity {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	/**********************************************************
	 * @param pavo - pavo.getId();
	 * @return AddressVO (or Map)
	 * select id, name, address, telephone, gender,
	 *        relationship, birthday, comments, registedate
	 * from mkaddrtb
	 * where id=:x
	 **********************************************************/
	
 // 오버로딩에 리턴타입은 영향X
	public AddressVO select(AddressVO vo) { // 무조건 한개 row만 가능
		return null;
	}
	
	public AddressVO[] select() { // n개 row가능
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, address, telephone, gender, relationship, birthday, comments, registedate");
		sql.append("from mkaddrtb");
		sql.append("where id=p_id");
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Vector<AddressVO> v = new Vector<>();
		AddressVO[] aVOS = null;
		
		try {
			con = dbMgr.getConnection();
		//	pstmt = con.prepareStatement(String.valueOg(sql);
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			AddressVO aVO = null;
			while(rs.next()) {
				aVO =  new AddressVO();
				aVO.setId(rs.getInt("id"));
				aVO.setName(rs.getNString("name"));
				aVO.setAddress(rs.getNString("address"));
				aVO.setTelephone(rs.getNString("telephone"));
				aVO.setGender(rs.getNString("gender"));
				aVO.setRelationship(rs.getNString("relationship"));
				aVO.setBirthday(rs.getNString("birthday"));
				aVO.setComments(rs.getNString("comments"));
				aVO.setRegistedate(rs.getNString("registedate"));
				v.add(aVO);
			}
			aVOS = new AddressVO[v.size()];
			v.copyInto(aVOS);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbMgr.freeConnection(con, pstmt, rs);
		}
		
		return null;
	}
	
	public List<AddressVO> selectList(AddressVO vo) { // n개 row가능
		return null;
	}
	
}