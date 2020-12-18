package tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
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

public class Ui {

	public static JFrame frame;
	private static JPanel game;
	private static HashMap<String, Component> things;
	private static JLabel Score;
	private JLabel HighScore;
	private JMenuItem pause;
	public JButton block[][] = new JButton[20][10];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Ui window = new Ui();
		window.frame.setVisible(true);
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
		info.setLayout(null);
		
		HighScore = new JLabel("<html>" + "HighScore" + "<br>" + "00000" + "<html>", SwingConstants.CENTER);
		HighScore.setBounds(0, 155, 326, 136);
		HighScore.setName("HighScore");
		HighScore.setBorder(gameLine);
		HighScore.setForeground(Color.white);
		info.add(HighScore);
		
		Score = new JLabel("<html>" + "Score" + "<br>" + "00000" + "<html>", SwingConstants.CENTER);
		Score.setBounds(0, 326, 326, 136);
		Score.setName("Score");
		Score.setBorder(gameLine);
		Score.setForeground(Color.white);
		info.add(Score);
		
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		
		JMenu fileTab = new JMenu("파일");
		menu.add(fileTab);
		
		JMenuItem saveGame = new JMenuItem("저장");
		fileTab.add(saveGame);
		
		JMenuItem loadGame = new JMenuItem("불러오기");
		fileTab.add(loadGame);
		
		pause = new JMenuItem("일시정지");
		fileTab.add(pause);
		
		JMenuItem endGame = new JMenuItem("게임종료");
		fileTab.add(endGame);
		
		JMenu settingTab = new JMenu("설정");
		menu.add(settingTab);
		
		makeMap();
	}
}
class keys implements KeyListener {
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			System.out.println("right");
		}//right
		if (e.getKeyCode() == 37) {
			System.out.println("left");

		}//left
		if (e.getKeyCode() == 38) {
		}//up
		if (e.getKeyCode() == 40) {
		}//down
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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