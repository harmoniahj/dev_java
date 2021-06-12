package athread.talk1;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TalkClient extends JFrame implements ActionListener { // 말하기 구현
 ///////////////// 통신과 관련된 전역변수 추가 /////////////////
	Socket socket = null; // N/W패키지
	ObjectOutputStream oos = null; // 직렬화 누릴수 있음(순서대로 보냄)
	ObjectInputStream ois = null;
	
	String nickName = null; // 닉네임
	
 ///////////////// 속지두장 추가 /////////////////
	JPanel jp_first = new JPanel();
	JPanel jp_seconde = new JPanel();
	JPanel jp_south = new JPanel();
 ///////////////// 화면과 관련된 전역변수 추가 /////////////////	
	JTextField jtf_msg = new JTextField(20);
	JTextArea jta_display = new JTextArea();
	JScrollPane jsp_display = new JScrollPane(jta_display);
	JButton jbtn_send = new JButton("전송");
	
 // 소켓관련 초기화	
	public void init() {
		try {
		// 소켓 생성하기 - ip, port - 서버측 ServerSocket연결됨.
			socket = new Socket("192.168.0.14", 3000);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(100 + "#" + nickName);
			
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();//run()호출
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void initDisplay() {
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		jtf_msg.addActionListener(this);
		jta_display.setEditable(false);
		jp_south.setLayout(new BorderLayout());
		
		jp_south.add("Center",jtf_msg);
		jp_south.add("East",jbtn_send);
		this.add("Center",jsp_display); 
		this.add("South",jp_south);
		
		this.setTitle(nickName + "님의 창");
		this.setVisible(true);
		this.setBounds(500, 300, 600, 600);
	}
	
	public static void main(String[] args) {
		TalkClient tc = new TalkClient();
		tc.initDisplay();
		tc.init(); // 소켓 처리
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		if(jtf_msg == obj) {
			try {
				oos.writeObject(200 + "#" +  nickName + "#" + msg + "\n");
				jtf_msg.setText("");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}