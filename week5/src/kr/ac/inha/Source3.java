package kr.ac.inha;

class Vehicle {
	String name = "Ż ��";
	void whoami() {
		System.out.println("���� Ż ���̴�.");
	}
	static void move() {
		System.out.println("�̵��ϴ�.");
	}
}
class Car extends Vehicle{
	String name = "�ڵ���";
	String name2 = "�ڵ���2";
	void whoami() {
		System.out.println("���� �ڵ����̴�.");
	}
	static void move() {
		System.out.println("�޸���.");
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
