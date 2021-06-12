package thread.bakery;

import java.util.Vector;

public class BakerStack {
	private Vector<String> v = new Vector<>();
	
	public synchronized String pop() {
		String bread = null;
	 // 빵이 진열대에 없나요?
		if(v.size()==0) {
			try {
				System.out.println("빵이 더 이상 없습니다. 잠시만 기다려 주세요.");
				this.wait();
			} catch (Exception e) {
				System.out.println("Exception : "+e.toString());
			}
		}
		bread = v.remove(v.size()-1);
		return bread;
	}
	
	public synchronized void push(String bread) {
		System.out.println("기다려 주셔서 감사합니다. 빵 나왔으니 가져가세요.");
		this.notify();
		v.add(bread);
	}
}