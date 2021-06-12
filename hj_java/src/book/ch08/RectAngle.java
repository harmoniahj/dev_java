package book.ch08;

public class RectAngle extends Shape {
	int width = 10;
	int height = 4;

	@Override
	void fillDraw() {
		
	}

	@Override
	void area() {
		double area = 0.0;
		area = width * height;
		System.out.println("사각형의 넓이는 " + area);
	}

}