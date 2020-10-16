package kr.ac.inha;

class Vehicle {
	String name = "탈 것";
	void whoami() {
		System.out.println("나는 탈 것이다.");
	}
	static void move() {
		System.out.println("이동하다.");
	}
}
class Car extends Vehicle{
	String name = "자동차";
	String name2 = "자동차2";
	void whoami() {
		System.out.println("나는 자동차이다.");
	}
	static void move() {
		System.out.println("달리다.");
	}
}
public class Source3 {
	public static void main(String[] args) {
		Vehicle v = new Car();
		System.out.println(v.name);
		v.whoami();
		v.move();
		Car c= new Car();
		System.out.println(c.name + c.name2);
		c.move();
	}
}
