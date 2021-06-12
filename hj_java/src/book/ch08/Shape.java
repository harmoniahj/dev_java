package book.ch08;

public abstract class Shape {
	String color = "skyblue";
	
	void draw() {
		System.out.printf("[ color = %s ]%n", color);
	}
	
	abstract void fillDraw();
	
	abstract void area();
 /*	abstract void fillDraw2() {
		
	}	*/
}