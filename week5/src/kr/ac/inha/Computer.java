package kr.ac.inha;

public class Computer implements Controllable {

	@Override
	public void turnon() {
		// TODO Auto-generated method stub
		System.out.println("컴퓨터를 켠다.");
	}

	@Override
	public void turnoff() {
		// TODO Auto-generated method stub
		System.out.println("컴퓨터를 끈다.");
	}

}
