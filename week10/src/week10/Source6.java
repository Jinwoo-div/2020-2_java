package week10;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Source6 extends JFrame{
	class MaPanel extends JPanel{ // 내부클래스
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("문자열을 그려보자", 20, 20);
			g.drawLine(20, 10, 30, 21);
		}
	}

}
