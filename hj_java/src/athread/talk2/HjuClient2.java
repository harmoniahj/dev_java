package athread.talk2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class HjuClient2 extends JFrame implements ActionListener {
 // 통신과 관련한 전역변수 추가
	Socket 				socket 	= null;
	ObjectOutputStream 	oos 	= null; // 말 하고 싶을 때
	ObjectInputStream 	ois		= null; // 듣기 할 때
	String 				nickName= null; // 닉네임 등록
	
	JPanel jp_second	  = new JPanel();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_one	  = new JButton("1:1");
	JButton jbtn_emotion	  = new JButton("이모티콘");
	JButton jbtn_fontsize	  = new JButton("글자크기");
	JButton jbtn_change	  = new JButton("대화명변경");
	JButton jbtn_font	  = new JButton("글자색");
	JButton jbtn_exit	  = new JButton("나가기");
	String cols[] 		  = {"대화명"};
	String data[][] 	  = new String[0][1];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable			  jtb = new JTable(dtm);
	JScrollPane       jsp = new JScrollPane(jtb);
	JPanel jp_first 		= new JPanel();
	JPanel jp_first_south 	= new JPanel();
	JTextField jtf_msg = new JTextField(20);//south속지 center
	JButton jbtn_send  = new JButton("전송");//south속지 east
 // JTextArea에는 문자열을 그리는 개념이 아닌 출력하는 개념이라 부분 색상 변셩, 문자열 그리기등이 X
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
	
	JTextPane jtp_display = new JTextPane(sd_display);
	JScrollPane jsp_display = new JScrollPane(jtp_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
														 , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	Image back = null;
	
	public HjuClient2() {
		jtf_msg.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_change.addActionListener(this);
	}
	
	public void initDisplay() {
	 // 사용자의 닉네임 받기
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		this.setLayout(new GridLayout(1,2));
		
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(3,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		back = getToolkit().getImage("src\\athread\\talk2\\back.jpg");
		
		Font font = new Font("돋움",Font.BOLD,25);
		jtp_display.setFont(font);	
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName);
		this.setSize(800, 500);
		this.setVisible(true);
	}
	
	public static void main(String args[]) {
	 // swing  skin사용
		JFrame.setDefaultLookAndFeelDecorated(true);
	 // 메인 스레드 우선권
		HjuClient2 hc = new HjuClient2();
	 // 화면 부름
		hc.initDisplay();
	 // 소켓 생성 - TS쪽 ServerSocket감지 - 일반 소켓 전달됨. - run메소드 안에서 - TomatoServerThread생성
	 // 생성자 호출(this)  - 듣기 가능해짐. - 전제 조건, oos, ois, 소켓객체 있어야 가능함.
		hc.init();
	}
	
 // 소켓 관련 초기화
	public void init() {
		try {
		 // 서버측의 ip주소 작성하기
			socket = new Socket("172.30.1.36", 3002);
		 // TS ServerSocket감지-> client = server.accept(); //클라이언트 소켓에 대한 정보 갖음.
		 // 홀수 소켓에 대한 처리
			oos = new ObjectOutputStream(socket.getOutputStream());
		 // 짝수 소켓에 대한 처리
			ois = new ObjectInputStream(socket.getInputStream());
		 // initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로 서버에게 내가 입장한 사실을 알린다.(말하기)
			oos.writeObject(Protocol.ROOM_IN + "#" + nickName); // 말했잖아
		 // HjuServerThread의 생성자가 듣기
		 // 서버에 말을 한 후 들을 준비를 한다. - 대기 탄다 - 듣기 - 프로토콜을 비교해야 함
		 // 프로토콜 설계하기 - ERD 그린다.- 데이터 클래스 설계 - List, Map 단위테스트  너가 해줄래  ^^
			HjuClientThread2 hct = new HjuClientThread2(this);
			hct.start();
		} catch (Exception e) {
		 // 예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
			System.out.println(e.toString());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		String msg = jtf_msg.getText();
		if(jbtn_one == obj) {
			
		}
		
		else if(jbtn_change == obj) {
			String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
			if(afterName==null || afterName.length()<3) {
				JOptionPane.showMessageDialog(this,"변경할 대화명을 입력하세요.","INFO",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				oos.writeObject(Protocol.CHANGE + Protocol.seperator+nickName
						   + Protocol.seperator+afterName
						   + Protocol.seperator+nickName+"님의 대화명이 "+afterName+"으로 변경");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		else if(jtf_msg == obj) {
			try {
				oos.writeObject(201 +"#"+nickName + "#" + msg);
				jtf_msg.setText("");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		else if(jbtn_exit==obj) {
			try {
				oos.writeObject(500 + "#" + this.nickName);
			 // 자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}
}
