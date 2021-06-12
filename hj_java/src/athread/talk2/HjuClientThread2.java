package athread.talk2;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;

/*******************************************************
 * 이벤트 핸들러의 역할 > 말하기
 * 클라이언트측의 스레드 역할 > 듣기 
 * @param tomatoClient
 *******************************************************/
public class HjuClientThread2 extends Thread{
	HjuClient2 hc = null;
	public HjuClientThread2(HjuClient2 hc) {
		this.hc = hc;
	}
	
	@Override
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = ""; // 100#apple
				msg = (String)hc.ois.readObject();
				StringTokenizer st = null;
				int protocol = 0; // 100|200|201|202|500
				
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken()); // 100
				}
				
				switch(protocol) {
					case Protocol.ROOM_IN:{ // 100#apple
						String nickName = st.nextToken();
					//	hc.jtp_display.append(nickName+"님이 입장하였습니다.\n");
						MutableAttributeSet attr = new SimpleAttributeSet();
						try {
							hc.sd_display.insertString(hc.sd_display.getLength(), "[" + nickName + "] 이 입장하였습니다. \n", attr);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						Vector<String> v = new Vector<>();
						v.add(nickName);
						hc.dtm.addRow(v);
					}break;
					case Protocol.MESSAGE:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						MutableAttributeSet attr = new SimpleAttributeSet();
						try {
							hc.sd_display.insertString(hc.sd_display.getLength(), "[" + nickName + "]" + message + "\n", attr);
						} catch (Exception e) {
							e.printStackTrace();
						}
					 // 새로 메시지가 들어올 때 자동으로 포커스 이동처리
						hc.jtp_display.setCaretPosition(hc.sd_display.getLength());					
					}break;
					case Protocol.CHANGE:{
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String msg1 = st.nextToken();
						for(int i=0;i<hc.dtm.getRowCount();i++) {
						 // 기존 대화명 가져오기
							String currentName = (String)hc.dtm.getValueAt(i, 0);
							if(currentName.equals(nickName)) {
							 // 테이블의 DefaultTableModel갱신처리
								hc.dtm.setValueAt(afterName, i, 0);
								break;
							}
						}
						try {
							hc.sd_display.insertString(hc.sd_display.getLength(), msg1+"\n", null);
						} catch (Exception e) {
							e.printStackTrace();
						}
						hc.jtp_display.setCaretPosition(hc.sd_display.getLength());
						if(nickName.equals(hc.nickName)) {
							hc.setTitle(afterName + "님의 대화창");
							hc.nickName = afterName; // 동기화 할것.... 중요
						}
					}break;
					case Protocol.ROOM_OUT:{
						String nickName = st.nextToken();
						MutableAttributeSet attr = new SimpleAttributeSet();
						try {
							hc.sd_display.insertString(hc.sd_display.getLength(), nickName+"님이 퇴장 하였습니다\n", attr);
						} catch (Exception e) {
							e.printStackTrace();
						}							
						hc.jtp_display.setCaretPosition(hc.sd_display.getLength());		
						for(int i=0; i<hc.dtm.getRowCount(); i++) {
							String n =(String)hc.dtm.getValueAt(i, 0);
							if(n.equals(nickName)) {
								hc.dtm.removeRow(i);
							}
						}
					}break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				}
		}
	}
	
	/*
	@Override
	public synchronized void run() {
		
	}
	@Override
	public void run() {
		synchronized(this) {
			
		}
	}
	*/
}