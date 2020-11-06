package week9;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Source8 extends JFrame{
	Source8() {
		setTitle("절대 위치로 배치!");
		
		JPanel p = new JPanel();
		p.setLayout(null);
		
		JButton b1 = new JButton();
		b1.setBounds(10, 10, 60, 40);//앞 두자리 시작좌표 뒤두자리 크기
		JButton b2 = new JButton();
		b1.setBounds(20, 10, 60, 40);
		JButton b3 = new JButton();
		b1.setBounds(30, 10, 60, 40);
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		add(p);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 110);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Source8();
	}

}
