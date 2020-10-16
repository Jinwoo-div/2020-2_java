package kr.ac.inha;

public interface Controllable {
	default void repair() {
		System.out.println("장비를 수리한다.");
	}
	static void reset() {
		System.out.println("장비를 초기화한다");
	}
	void turnon();
	void turnoff();
}
