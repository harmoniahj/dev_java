package book.ch14;

public class UserException extends Exception {
	private int port = 7000;
	
	public UserException() {}
	
	public UserException(String msg) {
		super(msg); // super > Exception
	}
	
	public UserException(String msg, int port) {
		super(msg); // super > Exception
	// 부모클래스에는 생성자 파라미터의 두번쨰 자리에 int 생성자X
	//	super(msg, port);
	}

	public int getPort() {
		return port;
	}
}