package week7;

public class Source5 {
	public static void main(String[] args) {
		Cup<Boricha> c = new Cup<Boricha>();
		c.setBeverage(new Boricha());//�������� �����߱⶧��  boricha���� �� new Boricha() -> �͸�ü ��ü ������ ���Ͼ����� �̸� ���� ���Ǿ��ϰ� ���
//		c.setBeverage(new Beer());// �ٸ� �ڷ��������Ϸ��� �ϸ� ����
//		Beer b = c.getBeverage();
		Boricha b = c.getBeverage();
	}
}

class Cup<T> {
	private T beverage;
	
	public T getBeverage() {
		return beverage;
	}
	
	public void setBeverage (T beverage) {
		this.beverage = beverage;
	}
}
class Boricha{}
class Beer{}
