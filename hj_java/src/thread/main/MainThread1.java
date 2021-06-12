package thread.main;

class MyrunningOne implements Runnable {

	@Override
	public void run() {
		System.out.println("run");
		first();
	}
	
	public void first() {
		System.out.println("first");
		second();
	}
	
	public void second() {
		System.out.println("second");
	}
}

public class MainThread1{
	
	public static void main(String[] args) {
		System.out.println("main start");
		Runnable run = new MyrunningOne();
		Thread th = new Thread(run);
		th.start();
	}

}