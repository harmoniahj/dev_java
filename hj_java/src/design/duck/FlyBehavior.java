package design.duck;

/*
 * 생성자 올수X, 메소드 이름 앞에 abstract 생략가능, 메소드 뒤에 ;으로 끝남
 */

public interface FlyBehavior {
//	static final int i = 0;
//	public FlyBehavior() {}	
	public abstract void fly(); // abstract 생략가능
}