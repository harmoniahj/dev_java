package thread.main;

import javax.swing.JFrame;

public class MainDisplay extends Thread {
	JFrame jf = null;
	
	public void initDisplay(){
		jf = new JFrame(); // 게으른 인스턴스화
		jf.setSize(400, 400);
		jf.setVisible(true);
	}
		
	public void run() {
		System.out.println("run 성공");
	}

	public static void main(String[] args) {
		MainDisplay md = new MainDisplay();
		md.initDisplay();
	}

}