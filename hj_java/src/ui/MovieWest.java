package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MovieWest extends JFrame {
	JButton jbtn_login = new JButton("로그인");
	JButton jbtn_search = new JButton("영화검색");
	JButton jbtn_res = new JButton("영화예약");
	
	public void initDisPlay() {
		this.setLayout(new GridLayout(12, 1, 3, 3));
		this.add(jbtn_login);
		this.add(jbtn_search);
		this.add(jbtn_res);
		
		this.setSize(150, 300);
		this.setVisible(true);
	}
}
