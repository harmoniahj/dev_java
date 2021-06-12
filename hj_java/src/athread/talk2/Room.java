package athread.talk2;

import java.util.List;
import java.util.Vector;

public class Room {
	private List<HjuServerThread> userList = new Vector<>();
	private List<String> nameList = new Vector<>();
	private String title = null;//단톡 이름, 제목
	private String state = null;//상태 관리하기
	public List<HjuServerThread> getUserList() {
		return userList;
	}
	public void setUserList(List<HjuServerThread> userList) {
		this.userList = userList;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
