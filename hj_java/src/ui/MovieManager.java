package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MovieManager extends JFrame {
	MovieWest mw = null;
	JPanel jp_west = null;
	JPanel jp_center = null;
	
	public void initDisplay() {
		jp_west = new JPanel();
		jp_center = new JPanel();
		jp_west.add(mw);
		
		mw = new MovieWest();
		this.add("West", jp_west);
		this.add("Center", jp_center);
		
		this.setSize(500, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MovieManager mm = new MovieManager();
		mm.initDisplay();
	}
}