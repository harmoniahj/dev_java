package level2.basic;

import javax.swing.JButton;
import javax.swing.JFrame;

public class A_2 extends JFrame {
	JButton jbtn = null;
	boolean isView = false; // boolean초기값은 false
	
	public A_2() {
		jbtn = new JButton("test2"); // NullPointerException
		initDisplay();
	}
	
	public A_2(boolean isView) {
		this.isView = isView;
		jbtn = new JButton("test3"); // 위치 중요, 출력
		initDisplay();
	}
	
	public void initDisplay() {
		this.add("Center", jbtn);
		this.setSize(500, 300); // 상수로 처리
		this.setVisible(isView);
	}

	public static void main(String[] args) {
		new A_2(true);
	}
}