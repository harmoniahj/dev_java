package thread.bakery;

public class Baker extends Thread {
	BakerStack bs = null;
	
	public Baker(BakerStack bs) {
		this.bs = bs;
	}
	
	@Override
	public void run() {
		String bread = null;
		bread = getBread();
		bs.push(bread);
		try {
			Thread.sleep(3000);//3초 정지
		} catch (InterruptedException e) {
			System.out.println("누구?");
		}
	}
	
	public String getBread() {
		String bread = null;
		switch((int)(Math.random() * 3)) {
			case 0:
				bread = "소보로";
				break;
			case 1:
				bread = "샌드위치";
				break;
			case 2:
				bread = "도너츠";
				break;
		}
		return bread;
	}
}