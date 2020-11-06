package week9;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Source3 extends JFrame{
	Source3() {	
		setTitle("안녕, 스윙!");
		JButton b = new JButton("버튼");
		add(b);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Source3();

	}
}
