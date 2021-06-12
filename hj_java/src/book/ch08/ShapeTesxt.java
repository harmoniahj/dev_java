package book.ch08;

public class ShapeTesxt {

	public static void main(String[] args) {
		Shape shape = new RectAngle();
		Shape shape2 = new Circle();
		
		shape.area();
		shape2.area();
	//	shape2.area(5); > 에러 이유 : 부모는 자식타입에 선언한 메소드 호출X
		
		Circle circle = new Circle();
		circle.area(5);
	}

}