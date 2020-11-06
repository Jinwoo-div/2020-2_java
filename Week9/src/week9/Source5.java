package week9;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class Source5 extends JFrame{
	Source5() {
		setTitle("보더 레이아웃");
		setLayout(new BorderLayout());
		add("East", new JButton("동"));
		add("West", new JButton("서"));
		add("South", new JButton("남"));
		add(new JButton("북"), BorderLayout.NORTH);
		add(new JButton("중앙"), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Source5();
	}
}
