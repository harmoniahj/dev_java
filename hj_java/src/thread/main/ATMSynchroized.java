package thread.main;

public class ATMSynchroized {

	public static void main(String[] args) {
		ATM atm = new ATM();
		Thread th = new Thread(atm, "hju");
		Thread th2 = new Thread(atm, "hj");
		
		th.start();
		th2.start();
	}

}