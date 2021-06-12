package level2.basic;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imsi extends JFrame {
	JPanel jp_north = new JPanel();
	JButton jbtn = new JButton("1");
	JButton jbtn2 = new JButton("2");
	JButton jbtn3 = new JButton("3");
	JLabel jlb = new JLabel("현재시간", JLabel.CENTER);
	Container cont = this.getContentPane();		
	
	ImsiCenterPanel jp = new ImsiCenterPanel();
	
	public Imsi() {
		jp_north.setLayout(new GridLayout(1, 3, 3 ,3));
		jp_north.add(jbtn);
		jp_north.add(jbtn2);
		jp_north.add(jbtn3);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼 호출");
				viewUpdate(e);
			}
		});
		
		jbtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("전체 화면 변경하기");
				viewAllUpdate(e);
			}
		});
		
		jbtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("패널을 다른패널로 변경");
				threeUpdate(e);
			}
		});
		
		this.add("North", jp_north);
		this.add("South", jlb);
		this.setSize(500, 500);
		this.setVisible(true);
	}
	
	protected void threeUpdate(ActionEvent e) {
		if(jp!=null) {
			cont.remove(jp.jbtn);
			cont.remove(jp.jbtn2);
		}
		
		JPanel jp2 = new JPanel();
		jp2.setBackground(new Color(206, 251, 201));
		this.add("Center", jp2);
		cont.revalidate();
	}

	protected void viewAllUpdate(ActionEvent e) {
	 // 선언부와 생성부를 다르게 하여 인스턴스화를 하면 인스턴스 변수로는 부모 타입에서 제공되는 메소드만 사용가능
	 // > 자녀 클래스의 메소드 호출 불가!! JPanel jp = new ImsiCenterPanel(); // 부모클래스만 사용가능
		jp.initDisplay();
		this.add("Center", jp);
		cont.revalidate();
	}

	public void viewUpdate(ActionEvent e) {
		System.out.println("viewUpdate 호출");
		cont.remove(jlb);
		cont.revalidate();
		this.remove(jbtn);
		this.revalidate();
		this.repaint();
	}

	public static void main(String[] args) {
		new Imsi();
	}

}