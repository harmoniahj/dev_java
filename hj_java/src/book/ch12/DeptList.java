package book.ch12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vo.DeptVO;

public class DeptList extends JFrame implements ActionListener {
	JButton jbtn_search = new JButton("조회");
	String cols[] = {"부서번호", "부서명", "지역"};

 // 데이터셋 선언
	DefaultTableModel dtm = null;
	JTable jtb = null;
	JScrollPane jsp = null;
	
 // 생성자
	public DeptList() {
		initDisplay(); // > 인스턴소화 될때 즉시 화면 그려짐
	}
	
	public void tableCreate() {
		dtm = new DefaultTableModel(new Object[3][3], cols);
		jtb = new JTable(dtm);
		jsp = new JScrollPane(jtb);
		this.add("North", jbtn_search);
		this.add("Center", jsp);
	}
	
 // 데이터 수집하기
	public List<Map<String, Object>> getDeptList() {
		Map<String, Object> r1 = new HashMap<>();
		List<Map<String, Object>> deptlist = new ArrayList<>();
		r1.put("deptno",  10);
		r1.put("dname",  "인사부");
		r1.put("loc",  "서울");
		deptlist.add(r1);
		
		r1 = new HashMap<>();;
		r1.put("deptno",  20);
		r1.put("dname",  "총무부");
		r1.put("loc",  "서울");
		deptlist.add(r1);
		
		r1 = new HashMap<>();;
		r1.put("deptno",  20);
		r1.put("dname",  "개발부");
		r1.put("loc",  "서울");
		deptlist.add(r1);
		return deptlist;
	}
	
	public List<DeptVO> getDeptList2() {
		System.out.println("getDeptList2()");
		return null;
	}
	
 // 화면처리
	public void initDisplay() {
		jbtn_search.addActionListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tableCreate(); // 메소드 호출도 동기화 필요 > 시점 문제
		this.setTitle("부서목록");
		this.setSize(400, 350);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new DeptList();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == jbtn_search) {
			List<Map<String, Object>> deptlist = getDeptList();
			Iterator<Map<String, Object>> iter = deptlist.iterator();
			Object keys[] = null;
			while(iter.hasNext()) {
				Map<String, Object> data = iter.next();
				keys = data.keySet().toArray();
				Vector oneRow = new Vector();
				oneRow.add(data.get(keys[2]));
				oneRow.add(data.get(keys[1]));
				oneRow.add(data.get(keys[0]));
				dtm.addRow(oneRow);
			}
		}
	}

}