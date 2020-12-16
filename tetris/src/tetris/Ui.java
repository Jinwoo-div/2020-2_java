package tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
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

public class Ui {

	private JFrame frame;
	private static JPanel game;
	private static HashMap<String, Component> things;
	private static JLabel Score;
	private JLabel HighScore;
	private JMenuItem pause;
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
	
	public static JLabel getThing(String name) {
		if (things.containsKey(name)) {
			return (JLabel) things.get(name);
		}
		else {
			return null;
		}
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
				JLabel tmp = new JLabel("");
				tmp.setBorder(gameLine);
				game.add(tmp);
				tmp.setName((i+1) + "_" + (j+1));
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