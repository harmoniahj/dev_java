package book.ch08;

public class Super {
	String title = null;
	
	public Super() {
		System.out.println("super() 호출 성공");
	}
	
	public Super(String title) {
		System.out.println("super(String) 호출 성공");
	}
	
	public void methodA() {
		System.out.println("Super methodA 호출성공");
	}
}