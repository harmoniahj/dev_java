package design.duck;

public class FlyNoWay implements FlyBehavior {

	@Override
	public void fly() {
		System.out.println("날지 못함 ㅠㅠ");
	}

}