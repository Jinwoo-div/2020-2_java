package week10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Source1 extends JFrame{
	Source1() {
		setTitle("hello event!!");
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("버튼을 클릭했습니다.");
			}
	};
		JButton b = new JButton("클릭");
		b.addActionListener(l);
		add(b);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Source1();
	}

}
