package athread.talk1;

import java.awt.Color;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * 클라이언트 측에서 접속 시도가 있을 때 서버측에서 클라이언트 로그 정보를  보고 싶음
 * 따라서 화면에 전광판이라 하나 추가하여 텍스트로 로그 정보를 확인 할 수 있도록 하기 위해 JFrame상속 받음
 * main메소드가 있는 클래스는 디폴트 스레드를 가짐 > 이 스레드에서 소켓 서버 정보를 관리하는 것이 경합이
 * 일어날 수 있고 그에 따라 충돌이나 이상이 발생 가능하므로 별도의 스레드를 구현하고  그 run메소드 안에서
 * 안전하게 소켓들이 생성되고 관리될 수 있도록 함
 */

public class TalkServer extends JFrame implements Runnable {
 /////////////// 통신과 관련된 전역변수 추가 /////////////////////////////
	ServerSocket server = null;
	Socket client = null;
	TalkServerThread tst = null;
	Vector<TalkServerThread> globalList = null; 

	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log);
	
	Color c = null;
	
	public void initDisplay() {
		c = new Color(255, 217, 250);
		jta_log.setBackground(c);
		
		this.add("Center",jsp_log);
		this.setBounds(500, 300, 600, 500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		TalkServer ts = new TalkServer();
		ts.initDisplay();
		Thread th = new Thread(ts); // 스레드에 대한 구현
		th.start(); // run메소드 호출.
	}

	@Override
	public void run() {
		System.out.println("run 호출 성공");
		globalList = new Vector<>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3000);
			jta_log.append("Server Ready .......\n");
			while(!isStop) {
				client = server.accept();
				jta_log.append("client info : "+client+"\n");
				TalkServerThread tst = new TalkServerThread(this);
				tst.start();
			}
		} catch (Exception e) {
			//예외 발생에 대한 메시지들을 stack영역에 관리하는데 이것들을 출력해주는 메소드 입니다.
			//라인 번호와 호출 순서에 대한 내용까지도 포함하니까 더 많은 힌트를 얻을 수가 있습니다.
			e.printStackTrace();
		}
	}
}