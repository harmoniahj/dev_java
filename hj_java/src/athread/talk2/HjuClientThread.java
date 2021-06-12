package athread.talk2;

import java.util.StringTokenizer;
import java.util.Vector;

/*******************************************************
 * 이벤트 핸들러의 역할 > 말하기
 * 클라이언트측의 스레드 역할 > 듣기 
 * @param tomatoClient
 *******************************************************/
public class HjuClientThread extends Thread{
	HjuClient hc = null;
	public HjuClientThread(HjuClient hc) {
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
						hc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						Vector<String> v = new Vector<>();
						v.add(nickName);
						hc.dtm.addRow(v);
					}break;
					case 200:{
						
					}break;
					case 201:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						hc.jta_display.append("["+nickName+"]"+message+"\n");
						hc.jta_display.setCaretPosition
						(hc.jta_display.getDocument().getLength());					
					}break;
					case 202:{

					}break;
					case 500:{
						String nickName = st.nextToken();
						hc.jta_display.append(nickName+"님이 퇴장 하였습니다.\n");
						hc.jta_display.setCaretPosition
						(hc.jta_display.getDocument().getLength());
						for(int i=0;i<hc.dtm.getRowCount();i++) {
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