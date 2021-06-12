package level2.basic;

import javax.swing.JButton;
import javax.swing.JFrame;

public class A_3 extends JFrame {
	JButton jbtn = null;
	boolean isView = false; // boolean초기값은 false
	
	public A_3() {
		jbtn = new JButton("test2"); // NullPointerException
		initDisplay();
	}
	
	public A_3(boolean isView) {
		this.isView = isView;
		jbtn = new JButton();
		jbtn.setText("전송A_3");
		initDisplay();
		jbtn.setText("전송A_3-1"); // 동기화 처리가 되고 있어서 괜찮음
	}
	
	public void initDisplay() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add("Center", jbtn);
		this.setSize(500, 300); // 상수로 처리
		this.setVisible(isView);
	}

	public static void main(String[] args) {
		new A_3(true);
	}
}