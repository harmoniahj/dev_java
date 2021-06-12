package di.step1;

public class HelloBeanImpl implements HelloBean {
	private String msg = null; // 싱글톤으로 만들어서
//	msg = new String("안녕~"); > 객체주입에서는 사용X

 // 생성자 객체 주입법(자바코드 적용), 객체 외부에서 주입받음
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getGreeting(String msg) {
		return "Spring" + this.msg;
	}
	
 // Bean이 초기화 된 후 호출되는 메소드
	public void initMethod() {
		System.out.println("initMethod 호출 성공");
	}
	
 // Bean이 소면되기 전에 호출되는 메소드
	public void destroyinitMethod() {
		System.out.println("destroyinitMethod 호출 성공");
	}
}