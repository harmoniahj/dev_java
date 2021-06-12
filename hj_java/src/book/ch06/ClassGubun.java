package book.ch06;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClassGubun extends JFrame {
	JButton jbtn_search = new JButton("조회");
	
	public void start() { // 익명클래스
		jbtn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("버튼");
			}
		
		});
	}
	
	public void initDispaly() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(jbtn_search);
		this.setSize(400, 300);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		ClassGubun c = new ClassGubun();
		c.start();
		c.initDispaly();
	}

}