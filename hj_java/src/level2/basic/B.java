package level2.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class B implements ActionListener {
	A a = null;
	C c = new C();
	public B(A a) {
//	public B(A abc) {
		System.out.println("B(A a) 생성자 호출 성공");
		this.a = a;
		//a = abc;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {//콜백메소드이다.자동 호출
		System.out.println("actionPerformed 호출 성공");
//		Object obj = ae.getSource();
//		Object c = ae.getSource().getClass();
		Object obj = ae.getSource().getClass();
// 		obj = ae.getSource().getClass();
		//만일 11번의 초기화가 없다면 NullPointerException발생.
//		if(a.jbtn==obj) {
		if(obj==a.jbtn.getClass()) {
			System.out.println("버튼 이벤트 발생했을 때 호출 됨.");
			String msg = c.methodA();
			System.out.println("메소드 처리결과 :"+msg);
		}
	}
}