package level2.basic;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ImsiCenterPanel extends JPanel {
	JButton jbtn = new JButton("1");
	JButton jbtn2 = new JButton("2");
	
	public void initDisplay() {
	//	JPanel jp = new ImsiCenterPanel();
		this.setBackground(new Color(232, 217, 255));
		this.add(jbtn);
		this.add(jbtn2);
	}
}