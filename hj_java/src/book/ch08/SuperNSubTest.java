package book.ch08;

public class SuperNSubTest {

	public static void main(String[] args) {
		Super sup = new Super();
		Super sup2 = new Sub();
		Sub sub = new Sub();
		
	//	sup = sup2; > 오른쪽ㅇ; 더 크면 안됨
		sup2 = sup;
		sup = sub;
		sub = (Sub)sup;
		sub.methodA();
	}
}