package athread.talk1;

import java.util.StringTokenizer;

public class TalkClientThread extends Thread{
	TalkClient tc = null; // 원본
	
	public TalkClientThread(TalkClient talkClient) {
		this.tc = talkClient; // 파라미터로 넘어온 전역변수 연결!!
	}

	@Override
	public void run() { // 말하는 곳
		System.out.println("run호출 성공");
		boolean isStop = false;
		while(!isStop) {
			try {
				String msg = "";
				msg = (String)tc.ois.readObject();
				
				StringTokenizer st = null; // #을 기준으로 문자열자름
				int protocol = 0;
				if(msg != null) { // 100#hj#집에 가고싶어(3개로 자름)
					st = new StringTokenizer(msg, "#");
					protocol = Integer.parseInt(st.nextToken()); // 100
				}
				switch(protocol) {
				case 100:{
					String nickName = st.nextToken(); // 집에 가고싶어
					tc.jta_display.append("[ " + nickName + " ]" + "님이 입장하였습니다\n");
				};
				case 200:{
					String nickName = st.nextToken();
					String msg2 =  st.nextToken();
					tc.jta_display.append("[ " + nickName + " ]" + msg2);
				};
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}