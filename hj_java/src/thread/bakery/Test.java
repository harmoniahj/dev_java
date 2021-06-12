package thread.bakery;

class MyThread  extends Thread {
	int n = 0;
	
	public MyThread(String name) {
		super(name);
	}
	
	public void run() {
		while(n<10) {
			System.out.println(this.getName() + " >> " + n);
			n++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

public class Test {

	public static void main(String[] args) {
		MyThread  my, my2;

		my = new MyThread("첫번째 스레드");
		my2 = new MyThread("두번째 스레드");
		
		my.start();
		my2.start();
	}

}
