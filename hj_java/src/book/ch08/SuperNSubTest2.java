package book.ch08;

public class SuperNSubTest2 {

	public static void main(String[] args) {
		Super sup = new Super();
		Super sup2 = new Sub();
		Sub sub = new Sub();
	//	Sub sub2 = new Super(); > 더큰것은X
		
		sup.methodA();
		sup2.methodA(); // 자녀 클래스의 메소드만 호출
		sub.methodA();
		sub.methodB();
	//	sub2.methodB(); X
	}

}
