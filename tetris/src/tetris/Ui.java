package tetris;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui window = new Ui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frame.setBounds(100, 100, 666, 708);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Border gameLine = BorderFactory.createLineBorder(Color.black);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel game = new JPanel();
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
		frame.getContentPane().add(info);
		info.setLayout(null);
		
		JLabel HighScore = new JLabel("0000");
		HighScore.setBounds(0, 155, 326, 136);
		info.add(HighScore);
		
		JLabel Score = new JLabel("0000");
		Score.setBounds(0, 326, 326, 136);
		info.add(Score);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileTab = new JMenu("파일");
		menuBar.add(fileTab);
		
		JMenuItem saveGame = new JMenuItem("저장");
		fileTab.add(saveGame);
		
		JMenuItem loadGame = new JMenuItem("불러오기");
		fileTab.add(loadGame);
		
		JMenuItem endGame = new JMenuItem("게임종료");
		fileTab.add(endGame);
		
		JMenu settingTab = new JMenu("설정");
		menuBar.add(settingTab);
		
	}
}