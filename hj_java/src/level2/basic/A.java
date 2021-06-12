package level2.basic;

import javax.swing.JButton;
import javax.swing.JFrame;

public class A extends JFrame {
	JButton jbtn = new JButton("test");;
	boolean isView = false; // boolean초기값은 false
	B b = null; // 타입만 결정 되었으므로 시점에 따라 NullPointException방지 해야 함
	C c = new C(); // A가 컴파일 될때 같이 메모리에 상주
	
	public A() {
		initDisplay();
	}
	
	public A(boolean isView) {
		this.isView = isView;
		initDisplay();
	}
	
	public void initDisplay() {
		b = new B(this);
		jbtn.addActionListener(b);
		this.add("Center", jbtn);
		this.setSize(500, 300); // 상수로 처리
		this.setVisible(isView);
	}

	public static void main(String[] args) {
		new A(true);
	}
}