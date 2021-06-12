package book.ch08;

public class Sub extends Super {
	public Sub() {
		System.out.println("Sub() 호출 성공");
	}
	
	public Sub(String title) {
		System.out.println("Sub(String)호출 성공");
	}
	
	@Override
	public void methodA() { // 메소드 오버로딩
		System.out.println("Sub methodA()호출성공");
	}
	
	public void methodA(int x) {
		System.out.println("Sub methodA(int x)호출성공");
	}
	
	public void methodB() {
		System.out.println("Sub methodB 호출성공");
	}
}