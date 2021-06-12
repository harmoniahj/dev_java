package design.duck;

public class MallardDuck extends Duck {
	public MallardDuck() {
		flyBehavior = new FllyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("청둥오리");
	}
}