package week10;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Source5 extends JFrame implements ActionListener {
	Source5() {
		setTitle("메뉴 구성하기");
		makeMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 170);
		setVisible(true);
	}
	void makeMenu() {
		JMenuItem item;
		KeyStroke key;
		
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("파일");
		m1.setMnemonic(KeyEvent.VK_F);
		JMenu m2 = new JMenu("색상");
		m2.setMnemonic(KeyEvent.VK_C);
		item = new JMenuItem("새 파일", KeyEvent.VK_N);
		item.addActionListener(this);
		m1.add(item);
		m1.add(new JMenuItem("파일 열기", KeyEvent.VK_O));
		item.addActionListener(this);
		m1.add(item);
		m1.add(new JMenuItem("파일 저장"));
		m1.addSeparator();
		m1.add(new JMenuItem("종료"));
		
		
		
	}

}
