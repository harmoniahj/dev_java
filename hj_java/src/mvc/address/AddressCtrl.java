package mvc.address;

import java.util.List;
import java.util.Map;

public class AddressCtrl {
	private AddressVO returnVO = new AddressVO();
	
 // 아래와 비교되는 값들은 모두 AddressBook(삭제>JTable에서)에서 받아오거나 ModifyDialog(입력, 수정)에서 받아와야 함
	
	private static String _DEL = "delete";
	private static String _INS = "insert";
	private static String _MOD = "update";
	private static String _SEL = "select";
	private static String _ALL = "selectall";

	public AddressCtrl() {
		
	}
// 파라미터로 VO를 받음 > 사용자가 선택 or 개발자가 필요로 하는 값을 넘길 수 있음
// returnVO > 파라미터로 받은 값을 처리 > 입력이 성공시 리터값이 1(입력성공) or 0(입력실패)
	public AddressVO send(AddressVO pvo) throws Exception {
		String command = pvo.getCommand();
		if(_DEL.equals(command)) {
			System.out.println("delete");
			DeleteEntity del = new DeleteEntity();
			returnVO = del.delete(pvo);
		}
		else if(_INS.equals(command)) { // 입력
			RegisterEntity regi = new RegisterEntity();
			returnVO = regi.insert(pvo);
		}
		else if(_MOD.equals(command)) {
			ModifyEntity modi = new ModifyEntity();
			returnVO = modi.update(pvo);
		}
		else if(_ALL.equals(command)) { // 전체조회
			System.out.println("전체조회");
			RetrieveEntity ret = new RetrieveEntity();
			returnVO = ret.select(pvo);
		}
		else if(_SEL.equals(command)) { // 상세조회
			RetrieveEntity ret = new RetrieveEntity();
			returnVO = ret.select(pvo);
		}
		return returnVO;
	}

	public AddressVO[] send() throws Exception {
		AddressVO[] returnVOs = null;
		return returnVOs;
	}

	public List<AddressVO> sendAll() {
		List<AddressVO> selectAll = null;
		RetrieveEntity ret = new RetrieveEntity();
		AddressVO pVO = new AddressVO();
		pVO.setCommand("selectall");
		ret.selectList(pVO);
		ret.selectList(null);
		return selectAll;
	}
	
	public List<Map<String, Object>> sendMap() throws Exception {
		List<Map<String, Object>> selectAll = null;
		return selectAll;
	}
}