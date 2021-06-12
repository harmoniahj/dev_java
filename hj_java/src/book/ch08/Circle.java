package book.ch08;

public class Circle extends Shape {
	final static double PI = 3.14;
	int r = 3;

	@Override
	void fillDraw() {
		// TODO Auto-generated method stub

	}

	@Override
	void area() {
		double area = r * r* PI;
		System.out.println("원의 넓이는 " + area);
	}
	
	void area(int r) {
		double area = r * r* PI;
		System.out.println("반지름이 " + r + "인 원의 넓이는 " + area);
	}
}