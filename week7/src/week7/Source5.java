package week7;

public class Source5 {
	public static void main(String[] args) {
		Cup<Boricha> c = new Cup<Boricha>();
		c.setBeverage(new Boricha());//보리차로 세팅했기때매  boricha형만 들어감 new Boricha() -> 익명객체 객체 딴데서 쓸일없을떄 이름 따로 정의안하고 사용
//		c.setBeverage(new Beer());// 다른 자료형변경하려고 하면 오류
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
