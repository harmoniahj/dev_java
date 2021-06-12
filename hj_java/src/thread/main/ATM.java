package thread.main;

public class ATM extends Thread {
	private int depositedMoney = 10000;
	
	public void run() {
		synchronized (this) {
			for(int i=0; i<10; i++) {
				try {
					Thread.sleep(3000); // 3초정도 지연
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				withDraw(1000);
			}
		} // end of synchronized
	} // end of run

 // 얼마 찾을거야??
	private void withDraw(int howMuch) {
		if(depositedMoney > 0) {
			depositedMoney -= howMuch;
			System.out.print(Thread.currentThread().getName() + " : "); // 스레드 이름
			System.out.printf("잔액 : %,d원 %n", depositedMoney); // %d = 10진수, %n : 줄바꿔줌
		}
		else {
			System.out.print(Thread.currentThread().getName() + " : ");
			System.out.println("잔액이 부족합니다!!");
		}
	}
}