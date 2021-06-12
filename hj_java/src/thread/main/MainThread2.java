package thread.main;

class MyrunningTwo implements Runnable {

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

public class MainThread2{
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "main start");
		Runnable run = new MyrunningOne();
		Thread th = new Thread(run);
		th.start();
		try {
			th.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} 
	}
}