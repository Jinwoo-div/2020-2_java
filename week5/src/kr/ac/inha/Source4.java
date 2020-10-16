package kr.ac.inha;

public class Source4 {
		public static void main (String[] args) {
			Controllable[] controllable = {new Tv(), new Computer()};
			
			for (Controllable c: controllable) {
				c.turnon();
				c.turnoff();
				c.repair();
			}
		}
}
