package week4;
class Circle {
	double radius;
	double static findArea() {
		return 3.14 * radius * radius;
	}
	void show (double x, double y) {
		System.out.printf("반지름= %.if, 넓이= %.if\n", x,y);
	}
}
public class test2 {
//	static double radius;
//	static double findArea() {
//		return 3.14 * radius * radius;
//	}
//	static void show(double x, double y) {
//		System.out.printf("반지름 = %.if, 넓이= %.if\n", x,y);
//	}
//	public static void main(String[] args) {
//		radius =10;
//		double area = findArea();
//		show (radius, area);
//	}
	Circle c = new Circle();
	c.radius = 10;
	c.show(c.radius, c.findArea());
}
