package tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ui {

	public static JFrame frame;
	private static JPanel game;
	private static HashMap<String, Component> things;
	public static JLabel Score;
	public static JLabel HighScore;
	public JButton block[][] = new JButton[20][10];
	public static JMenuItem saveGame;
	public static JMenuItem loadGame;
	public static JButton pause;
	public static JPanel panel;
	public static JButton btnNewButton;
	public static JButton btnNewButton_1;
	public static JPanel showNext;
	public static JButton b31;
	public static JButton b11;
	public static JButton b12;
	public static JButton b21;
	public static JButton b22;
	public static JButton b41;
	public static JButton b32;
	public static JButton b42;
	public static JMenuItem endProgram;
	public static JMenuItem endGame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Ui window = new Ui();
		window.frame.setVisible(true);
	}
	public static void restart() {
		frame.dispose();
	}

	public void makeMap() {
		things = new HashMap<String, Component>();
		Component[] components = game.getComponents();
		for (int i = 0; i < components.length; i++) {
			things.put(components[i].getName(), components[i]);
		}
		things.put(Score.getName(), Score);
		things.put(HighScore.getName(), HighScore);
	}
	
	public static JButton getThing(String name) {
		if (things.containsKey(name)) {
			return (JButton) things.get(name);
		}
		else {
			return null;
		}
	}
	public static JPanel returnPanel() {
		return game;
	}
	/**
	 * Create the application.
	 */
	public Ui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.black);
		frame.setBounds(100, 100, 666, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border gameLine = BorderFactory.createLineBorder(Color.white);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		game = new JPanel();
		game.setBackground(Color.black);
		frame.getContentPane().add(game);
		game.setLayout(new GridLayout(20, 10, 0, 0));
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				block[i][j] = new JButton();
				JButton tmp = block[i][j];
				tmp.setBorder(gameLine);
				game.add(tmp);
				tmp.setName((i+1) + "_" + (j+1));
				tmp.setBackground(Color.black);
			}
		}
		JPanel info = new JPanel();
		info.setBackground(Color.black);
		frame.getContentPane().add(info);
		info.setLayout(new GridLayout(0, 1, 0, 0));
		
		HighScore = new JLabel("<html>" + "HighScore" + "<br>" + "00000" + "<html>", SwingConstants.CENTER);
		HighScore.setName("HighScore");
		HighScore.setBorder(gameLine);
		HighScore.setForeground(Color.white);
		info.add(HighScore);
		
		Score = new JLabel("<html>" + "Score" + "<br>" + "00000" + "<html>", SwingConstants.CENTER);
		Score.setName("Score");
		Score.setBorder(gameLine);
		Score.setForeground(Color.white);
		info.add(Score);
		
		panel = new JPanel();
		info.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		showNext = new JPanel();
		panel.add(showNext);
		showNext.setLayout(new GridLayout(4, 2, 0, 0));
		
		b11 = new JButton("");
		b11.setBackground(Color.black);
		b11.setBorder(gameLine);
		showNext.add(b11);
		
		b12 = new JButton("");
		b12.setBackground(Color.black);
		b12.setBorder(gameLine);
		showNext.add(b12);
		
		b21 = new JButton("");
		b21.setBackground(Color.black);
		b21.setBorder(gameLine);
		showNext.add(b21);
		
		b22 = new JButton("");
		b22.setBackground(Color.black);
		b22.setBorder(gameLine);
		showNext.add(b22);
		
		b31 = new JButton("");
		b31.setBackground(Color.black);
		b31.setBorder(gameLine);
		showNext.add(b31);
		
		b32 = new JButton("");
		b32.setBackground(Color.black);
		b32.setBorder(gameLine);
		showNext.add(b32);
		
		b41 = new JButton("");
		b41.setBackground(Color.black);
		b41.setBorder(gameLine);
		showNext.add(b41);
		
		b42 = new JButton("");
		b42.setBackground(Color.black);
		b42.setBorder(gameLine);
		showNext.add(b42);
		
		
		btnNewButton_1 = new JButton();
		btnNewButton_1.setBackground(Color.black);
		panel.add(btnNewButton_1);
		
		btnNewButton = new JButton();
		btnNewButton.setBackground(Color.black);
		panel.add(btnNewButton);
		
		pause = new JButton("일시정지");
		info.add(pause);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		menu.setFocusable(false);
		JMenu fileTab = new JMenu("메뉴");
		menu.add(fileTab);
		
		saveGame = new JMenuItem("저장");
		fileTab.add(saveGame);
		
		loadGame = new JMenuItem("불러오기");
		fileTab.add(loadGame);
		
		endGame = new JMenuItem("게임 종료");
		fileTab.add(endGame);
		
		endProgram = new JMenuItem("프로그램 종료");
		endProgram.setActionCommand("");
		fileTab.add(endProgram);
		
		makeMap();
	}
	public JMenuItem getLoadGame() {
		return loadGame;
	}
	public JLabel getScore() {
		return Score;
	}
	public JLabel getHighScore() {
		return HighScore;
	}
	public JButton getB11() {
		return b11;
	}
	public JButton getB12() {
		return b12;
	}
	public JButton getB21() {
		return b21;
	}
	public JButton getB22() {
		return b22;
	}
	public JButton getB31() {
		return b31;
	}
	public JButton getB32() {
		return b32;
	}
	public JButton getB41() {
		return b41;
	}
	public JButton getB42() {
		return b42;
	}
	public JMenuItem getEndProgram() {
		return endProgram;
	}
}
//class block implements Runnable {
//	public ArrayList<Color> blockColor = new ArrayList<Color>();
//	public ArrayList<Integer> order = new ArrayList<Integer>();
//	public int shape = 1;
//	public int curPos[][] = new int[4][];
//	public int count = 1;
//	public block() {
//	}
//	public void run() {
//		initColor();
//		for(int i = 0; i < 7; i++) {
//			order.add(i+1);
//		}
//		while (true) {
//			if (count == 1) {
//				Collections.shuffle(blockColor);
//				Collections.shuffle(order);
//			}
//			setBlock();
//			while(isFloor() == false) {
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				dropBlock();
//			}
//			count++;
//			if (count == 7) {
//				count = 1;
//			}
//		}
//	}
//	public void dropBlock() {
//		Color col = blockColor.get(count);
//		
//		for (int i = 3; i > -1; i--) {
//			JLabel tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
//			tmp.setBackground(Color.black);
//			tmp = Ui.getThing((curPos[i][0]+1) + "_" + curPos[i][1]);
//			tmp.setBackground(col);
//		}
//		for (int i = 0; i < 4; i++) {
//			curPos[i][0] += 1;
//		}
//	}
//
//	public boolean isFloor() {
//		String name = (curPos[3][0]+1) + "_" + curPos[3][1];
//		if (curPos[3][0] == 20) return true;
//		Color next = Ui.getThing(name).getBackground();
//		System.out.println(next);
//		Color black = Ui.getThing("Score").getBackground();
//		System.out.println(black);
//		if(next == black) {
//			return false;
//		}
//		return true;
//	}
//	
//	public void setBlock() {
//		Color col = blockColor.get(count);
//		int pos = setPosition(count);
//		int ord = order.get(count);
//		if (ord == 7) {//I
//			for (int i = 0; i < 4; i++) {
//				JLabel a= Ui.getThing((i+1) + "_" + pos);
//				a.setBackground(col);
//				curPos[i] = new int[]{i+1, pos};
//			}
//		}
//		else if (ord == 6) {//O
//			Ui.getThing("1_" + pos).setOpaque(true);
//			Ui.getThing("1_" + (pos+1)).setOpaque(true);
//			Ui.getThing("2_" + pos).setOpaque(true);
//			Ui.getThing("2_" + (pos+1)).setOpaque(true);
//			Ui.getThing("1_" + pos).setBackground(col);
//			Ui.getThing("1_" + (pos+1)).setBackground(col);
//			Ui.getThing("2_" + pos).setBackground(col);
//			Ui.getThing("2_" + (pos+1)).setBackground(col);
//			curPos[0] = new int[]{1, pos};
//			curPos[1] = new int[]{1, pos+1};
//			curPos[2] = new int[]{2, pos};
//			curPos[3] = new int[]{2, pos+1};
//		}
//		else if (ord == 5) {//S
//			Ui.getThing("1_" + (pos+1)).setOpaque(true);
//			Ui.getThing("2_" + (pos+1)).setOpaque(true);
//			Ui.getThing("2_" + pos).setOpaque(true);
//			Ui.getThing("3_" + pos).setOpaque(true);
//			Ui.getThing("1_" + (pos+1)).setBackground(col);
//			Ui.getThing("2_" + (pos+1)).setBackground(col);
//			Ui.getThing("2_" + pos).setBackground(col);
//			Ui.getThing("3_" + pos).setBackground(col);
//			curPos[0] = new int[]{1, pos+1};
//			curPos[1] = new int[]{2, pos+1};
//			curPos[2] = new int[]{2, pos};
//			curPos[3] = new int[]{3, pos};
//		}
//		else if (ord == 4) {//S
//			Ui.getThing("1_" + pos).setOpaque(true);
//			Ui.getThing("2_" + pos).setOpaque(true);
//			Ui.getThing("2_" + (pos+1)).setOpaque(true);
//			Ui.getThing("3_" + (pos+1)).setOpaque(true);
//			Ui.getThing("1_" + pos).setBackground(col);
//			Ui.getThing("2_" + pos).setBackground(col);
//			Ui.getThing("2_" + (pos+1)).setBackground(col);
//			Ui.getThing("3_" + (pos+1)).setBackground(col);
//			curPos[0] = new int[]{1, pos};
//			curPos[1] = new int[]{2, pos};
//			curPos[2] = new int[]{2, pos+1};
//			curPos[3] = new int[]{3, pos+1};
//		}
//		else if (ord == 3) {//L
//			Ui.getThing("1_" + pos).setOpaque(true);
//			Ui.getThing("2_" + pos).setOpaque(true);
//			Ui.getThing("3_" + pos).setOpaque(true);
//			Ui.getThing("3_" + (pos+1)).setOpaque(true);
//			Ui.getThing("1_" + pos).setBackground(col);
//			Ui.getThing("2_" + pos).setBackground(col);
//			Ui.getThing("3_" + pos).setBackground(col);
//			Ui.getThing("3_" + (pos+1)).setBackground(col);
//			curPos[0] = new int[]{1, pos};
//			curPos[1] = new int[]{2, pos};
//			curPos[2] = new int[]{3, pos};
//			curPos[3] = new int[]{3, pos+1};
//		}
//		else if (ord == 2) {//L
//			Ui.getThing("1_" + (pos+1)).setOpaque(true);
//			Ui.getThing("2_" + (pos+1)).setOpaque(true);
//			Ui.getThing("3_" + (pos+1)).setOpaque(true);
//			Ui.getThing("3_" + pos).setOpaque(true);
//			Ui.getThing("1_" + (pos+1)).setBackground(col);
//			Ui.getThing("2_" + (pos+1)).setBackground(col);
//			Ui.getThing("3_" + (pos+1)).setBackground(col);
//			Ui.getThing("3_" + pos).setBackground(col);
//			curPos[0] = new int[]{1, pos+1};
//			curPos[1] = new int[]{2, pos+1};
//			curPos[2] = new int[]{3, pos+1};
//			curPos[3] = new int[]{3, pos};
//		}
//		else if (ord == 1) {//t
//			Ui.getThing("1_" + pos).setOpaque(true);
//			Ui.getThing("2_" + pos).setOpaque(true);
//			Ui.getThing("3_" + pos).setOpaque(true);
//			Ui.getThing("2_" + (pos+1)).setOpaque(true);
//			Ui.getThing("1_" + pos).setBackground(col);
//			Ui.getThing("2_" + pos).setBackground(col);
//			Ui.getThing("3_" + pos).setBackground(col);
//			Ui.getThing("2_" + (pos+1)).setBackground(col);
//			curPos[0] = new int[]{1, pos};
//			curPos[1] = new int[]{2, pos};
//			curPos[2] = new int[]{2, pos+1};
//			curPos[3] = new int[]{3, pos};
//		}
//	}
//	public int setPosition(int num) {
//		int seed;
//		if (num == 7) {
//			seed = 10;
//		}
//		else {
//			seed = 9;
//		}
//		Random rand = new Random();
//		rand.setSeed(System.currentTimeMillis());
//		return rand.nextInt(seed)+1;
//	}
//	public void initColor() {
//		blockColor.add(Color.red);
//		blockColor.add(Color.orange);
//		blockColor.add(Color.yellow);
//		blockColor.add(Color.green);
//		blockColor.add(Color.blue);
//		blockColor.add(Color.magenta);
//		blockColor.add(Color.pink);
//	}
//}