package kr.ac.inha;

public interface Controllable {
	default void repair() {
		System.out.println("��� �����Ѵ�.");
	}
	static void reset() {
		System.out.println("��� �ʱ�ȭ�Ѵ�");
	}
	void turnon();
	void turnoff();
}
