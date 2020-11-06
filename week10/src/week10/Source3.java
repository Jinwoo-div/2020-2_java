package week10;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class Source3 extends JFrame{
	Source3() {
		JLabel label = new JLabel("", JLabel.CENTER);
		JScrollBar bar = new JScrollBar(JScrollBar.HORIZONTAL);
		bar.setValues(50, 10, 0, 100);
		bar.addAdjustmentListener(e->{
			int v = e.getValue();
			label.setText("위치: " + v);
		});
		add("Center", label);
		add("North", bar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Source3();
	}
}
