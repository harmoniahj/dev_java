package level2.basic;

import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.w3c.dom.events.MouseEvent;

public class Quiz1 extends JFrame implements MouseListener{
	JLabel jlb = new JLabel("안녕");
	JTextArea jta = new JTextArea();
	
	public void initDisplay() {
		jlb.addMouseListener(this);
		this.add("Center", jta);
		this.add("South", jlb);
		this.setSize(600, 450);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Quiz1 q1 = new Quiz1();
		q1.initDisplay();
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("JLabel");
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}