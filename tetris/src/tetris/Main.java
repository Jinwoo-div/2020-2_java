package tetris;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tetris.Ui;

public class Main {
	public static block start;
	public static boolean restart = false;
	public static void main(String[] args) {
		Ui.main(args);
		start = new block();
		Ui.frame.setFocusable(true);
		Ui.frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 39) {
					start.movingRight = true;
				}//right
				if (e.getKeyCode() == 37) {
					start.movingLeft = true;
				}//left
				if (e.getKeyCode() == 38) {
					start.rotate = true;
				}//up
				if (e.getKeyCode() == 40) {
					start.down = true;
				}//down
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 40) {
					start.down = false;
				}//down
				// TODO Auto-generated method stub
				
			}

		});
		Ui.saveGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					start.save = true;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Ui.loadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					start.load = true;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Ui.pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (start.pause == false) {
					start.pause = true;
					Ui.pause.setText("재개");
				}
				else if (start.pause == true) {
					start.pause = false;
					Ui.pause.setText("일시정지");
				}
			}
		});
		Ui.endProgram.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					System.exit(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Ui.endGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (restart == false) {
						Ui.endGame.setText("게임 시작");
						start.nowOver = true;
						restart = true;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		start.run();
	}
}

class block implements Runnable {
	public ArrayList<Color> blockColor = new ArrayList<Color>();
	public ArrayList<Integer> order = new ArrayList<Integer>();
	public int shape = 1;
	public int curPos[][] = new int[4][];
	public int count = 0;
	public int myScore = 0;
	public boolean movingLeft = false;
	public boolean movingRight = false;
	public boolean rotate = false;
	public boolean down = false;
	public boolean save = false;
	public boolean load = false;
	public boolean over = false;
	public boolean pause = false;
	public boolean nowOver = false;
	public block() {
	}
	public void run() {
		initColor();
		initHigh();
		for(int i = 0; i < 7; i++) {
			order.add(i);
		}
		while (true) {
			if (count == 0) {
				Collections.shuffle(blockColor);
				Collections.shuffle(order);
			}
			setBlock();
			while(isFloor() == false) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dropBlock();
				if (movingLeft == true) {
					blockLeftRight(false);
					movingLeft = false;
				}
				else if (movingRight == true) {
					blockLeftRight(true);
					movingRight = false;
				}
				else if (rotate == true) {
					rotateBlock();
					rotate = false;
				}
				else if (down == true) {
					while (isFloor() == false && down == true) {
						try {
							Thread.sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dropBlock();
					}
					down = false;
				}
				else if (load == true) {
					load();
					load = false;
				}

				else if (save == true) {
					try {
						save();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					save = false;
				}
				else if (nowOver == true) {
					break;
				}
				while (true) {
					if (pause == false) {
						System.out.println("");
						break;
					}
					else {
						System.out.println("");
					}
				}
			}
			if (nowOver == true) {
				String data = Ui.Score.getText();
				String[] arr = data.split(">");
				String[] arr2 = arr[2].split("<");
				int score = Integer.parseInt(arr2[0]);
				String noti = "게임종료/점수:" + score;
				JOptionPane.showMessageDialog(null, noti, "알림",JOptionPane.ERROR_MESSAGE);
				eraseAll();
				break;
			}
			if (over == true) {
				endWorking();
				break;
			}
			checkBreak();
			checkOver();
			count++;
			shape = 1;
			if (count == 7) {
				count = 0;
			}
		}
	}
	public void eraseAll() {
		for (int i = 1; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				JButton tmp = Ui.getThing(i + "_" + j);
				tmp.setBackground(Color.black);
			}
		}
		initNext();
	}
	public void initHigh() {
		File dat = new File("C:/homeWork/tetScore.txt");
		String resource = "";
		try {
			Scanner dataScan = new Scanner(dat);
			while (dataScan.hasNextLine()) {
				resource = resource + dataScan.nextLine();
			}
			dataScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int high = Integer.parseInt(resource);
		String newHigh = "<html>HighScore<br>" + high + "<html>";
		Ui.HighScore.setText(newHigh);
	}
	public void endWorking() {
		File dat = new File("C:/homeWork/tetScore.txt");
		String resource = "";
		try {
			Scanner dataScan = new Scanner(dat);
			while (dataScan.hasNextLine()) {
				resource = resource + dataScan.nextLine();
			}
			dataScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int high = Integer.parseInt(resource);
		String data = Ui.Score.getText();
		String[] arr = data.split(">");
		String[] arr2 = arr[2].split("<");
		int score = Integer.parseInt(arr2[0]);
		String noti = "게임오버/스코어:" + score;
		JOptionPane.showMessageDialog(null, noti, "알림",JOptionPane.ERROR_MESSAGE);
		if (high > score) {
		}else {
			BufferedOutputStream bs = null;
			String newHigh = "<html>HighScore<br>" + score + "<html>";
			String in = Integer.toString(score);
			try{
				bs = new BufferedOutputStream(new FileOutputStream("C:/homeWork/tetScore.txt"));
				bs.write(in.getBytes());
			}	catch (Exception e) {
			e.getStackTrace();
			}	finally {
				try {
					bs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Ui.HighScore.setText(newHigh);
		}
		
	}
	public void checkOver() {
		for (int i = 1; i < 11; i++) {
			if (Ui.getThing(1 + "_" + i).getBackground() != Color.black) {
				over = true;
			}
		}
	}
	public void save() throws IOException {
		String data = "";
		for (int i =1; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				JButton tmp = Ui.getThing(i + "_" + j);
				if (tmp.getBackground() == Color.black) {
					data = data + "//b";
				}
				else {
					data = data + "//" + Integer.toString(tmp.getBackground().getRGB());
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				data = data + "//" + curPos[i][j];
			}
		}
		data = data + "//" + order.get(count);
		data = data + "//" + Integer.toString(blockColor.get(count).getRGB());
		BufferedOutputStream bs = null;
		try{
			bs = new BufferedOutputStream(new FileOutputStream("C:/homeWork/tetData.txt"));
			bs.write(data.getBytes());
		}	catch (Exception e) {
		e.getStackTrace();
		} 	finally {
			bs.close();
		}
		
	}
	public void load() {
		File data = new File("C:/homeWork/tetData.txt");
		String resource = "";
		try {
			Scanner dataScan = new Scanner(data);
			while (dataScan.hasNextLine()) {
				resource = resource + dataScan.nextLine();
			}
			dataScan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] res = resource.split("//");
		int con = 1;
		for (int i =1; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				JButton tmp = Ui.getThing(i + "_" + j);
				Color co = Color.black;
				if (res[con].equals("b")) {
				}
				else {
					co = new Color(Integer.parseInt(res[con]));
				}
				tmp.setBackground(co);
				con++;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				curPos[i][j] = Integer.parseInt(res[con]);
				con++;
			}
		}
		order.set(count, Integer.parseInt(res[con]));
		con++;
		blockColor.set(count, new Color(Integer.parseInt(res[con])));
	}
	public int[] checkBreak() {
		ArrayList<Integer> liner = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			int line = curPos[i][0];
			for (int j= 1; j < 11; j++) {
				if (Ui.getThing(line + "_" + j).getBackground() == Color.black){
					break;
				}
				if (j == 10) {
					for (int k = 0; k < liner.size(); k++) {
						if (liner.get(k) == line) {
							break;
						}
						if (k == liner.size() - 1) {
							liner.add(line);
						}
					}
					if (liner.size() == 0) {
						liner.add(line);
					}
				}
			}
		}
		Collections.sort(liner);
		for (int i = liner.size()-1 ; i > -1; i--) {
			int line = liner.get(i);
			for (int j = 1; j < 11; j++) {
				JButton tmp = Ui.getThing(line + "_" + j);
				tmp.setBackground(Color.black);
			}
			myScore += 10;
			String newHigh = "<html>Score<br>" + myScore + "<html>";
			Ui.Score.setText(newHigh);
		}
		for (int i = liner.size()-1 ; i > -1; i--) {
			int line = liner.get(i);
			int endLine;
			if (i == 0) {
				endLine = 0;
			}
			else {
				endLine = liner.get(i-1);
			}
			for (int j = line; j > 0; j--) {
				for(int k = 1; k < 11; k++) {
					Color colo = Ui.getThing(j + "_" + k).getBackground();
					if (colo == Color.black) {
						continue;
					}
					JButton tmp = Ui.getThing(j + "_" + k);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing((j+1) + "_" + k);
					tmp.setBackground(colo);
				}
			}
		}
		int li[] = new int [liner.size()];
		for (int i = 0; i < liner.size(); i++) {
			li[i] = liner.get(i);
		}
		return li;//없애야할 줄들 리턴 여기까지....
	}
	public void rotateBlock() {
		Color col = blockColor.get(count);
		if (order.get(count) == 6) {
			if (shape == 1) {
				if (curPos[0][0]+2 > 20 || curPos[0][1]-2 < 1 || curPos[1][0]+1 > 20 || 
						curPos[1][1]-1 < 1 || curPos[3][0]-1 < 1 || curPos[3][1]+1 > 10) return;
				if (Ui.getThing((curPos[0][0]+2) + "_" + (curPos[0][1]-2)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[1][0]+1) + "_" + (curPos[1][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]-1) + "_" + (curPos[3][1]+1)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+2) + "_" + (curPos[0][1]-2));
				tmp.setBackground(col);
				curPos[0][0] += 2;
				curPos[0][1] -= 2;
				tmp = Ui.getThing(curPos[1][0] + "_" + curPos[1][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[1][0]+1) + "_" + (curPos[1][1]-1));
				tmp.setBackground(col);
				curPos[1][0] += 1;
				curPos[1][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]-1) + "_" + (curPos[3][1]+1));
				tmp.setBackground(col);
				curPos[3][0] -= 1;
				curPos[3][1] += 1;
				shape = 2;
			}
			else {
				if (curPos[0][0]-2 < 1 || curPos[0][1]+2 > 10 || curPos[1][0]-1 < 1 || 
						curPos[1][1]+1 > 10 || curPos[3][0]+1 > 20 || curPos[3][1]-1 < 1) return;
				if (Ui.getThing((curPos[0][0]-2) + "_" + (curPos[0][1]+2)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[1][0]-1) + "_" + (curPos[1][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]+1) + "_" + (curPos[3][1]-1)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-2) + "_" + (curPos[0][1]+2));
				tmp.setBackground(col);
				curPos[0][0] -= 2;
				curPos[0][1] += 2;
				tmp = Ui.getThing(curPos[1][0] + "_" + curPos[1][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[1][0]-1) + "_" + (curPos[1][1]+1));
				tmp.setBackground(col);
				curPos[1][0] -= 1;
				curPos[1][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]+1) + "_" + (curPos[3][1]-1));
				tmp.setBackground(col);
				curPos[3][0] += 1;
				curPos[3][1] -= 1;
				shape = 1;
			}
		}
		if (order.get(count) == 3) {
			if (shape == 1) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 < 1 || curPos[2][0]-1 < 1 || 
						curPos[2][1]-1 < 1 || curPos[3][0]-2 < 1) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] -= 2;
				shape = 2;
			}
			else {
				if (curPos[0][0]+1 > 20 || curPos[0][1]+1 > 10 || curPos[2][0]+1 > 20 || 
						curPos[2][1]+1 > 10 || curPos[3][0]+2 > 20) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] += 2;
				shape = 1;
			}
		}
		if (order.get(count) == 4) {
			if (shape == 1) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 < 1 || curPos[2][0]+1 > 20 || 
						curPos[2][1]+1 > 10 || curPos[3][1]+2 > 10) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2));
				tmp.setBackground(col);
				curPos[3][1] += 2;
				shape = 2;
			}
			else {
				if (curPos[0][0]-1 < 1 || curPos[0][1]+1 > 10 || curPos[2][0]-1 < 1 || 
						curPos[2][1]-1 < 1 || curPos[3][1]-2 < 1) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2));
				tmp.setBackground(col);
				curPos[3][1] -= 2;
				shape = 1;
			}	
		}
		if (order.get(count) == 2) {
			if (shape == 1) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 > 10 || curPos[2][0]-1 < 1 || 
						curPos[2][1]+1 > 10 || curPos[3][1]-2 < 1) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2));
				tmp.setBackground(col);
				curPos[3][1] -= 2;
				shape = 2;
			}
			else if (shape == 2) {
				if (curPos[0][0]-1 < 1 || curPos[0][1]+1 > 10 || curPos[2][0]+1 > 20|| 
						curPos[2][1]-1 < 1 || curPos[3][0]-2 < 1) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] -= 2;
				shape = 3;
			}
			else if (shape == 3) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 < 1 || curPos[2][0]-1 < 1|| 
						curPos[2][1]+1 > 10 || curPos[3][1]+2 > 20) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2));
				tmp.setBackground(col);
				curPos[3][1] += 2;
				shape = 4;
			}
			else if (shape == 4) {
				if (curPos[0][0]-1 < 1 || curPos[0][1]+1 > 10 || curPos[2][0]+1 > 20|| 
						curPos[2][1]-1 < 1 || curPos[3][0]+2 > 20) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] += 2;
				shape = 1;
			}
		}
		if (order.get(count) == 1) {
			if (shape == 1) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 > 10 || curPos[2][0]-1 < 1 || 
						curPos[2][1]+1 > 10 || curPos[3][0]-2 < 1) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]-2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] -= 2;
				shape = 2;
			}
			else if (shape == 2) {
				if (curPos[0][0]-1 < 1 || curPos[0][1]+1 > 10 || curPos[2][0]+1 > 20|| 
						curPos[2][1]-1 < 1 || curPos[3][1]+2 < 1) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]+2));
				tmp.setBackground(col);
				curPos[3][1] += 2;
				shape = 3;
			}
			else if (shape == 3) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 < 1 || curPos[2][0]-1 < 1|| 
						curPos[2][1]+1 > 10 || curPos[3][0]+2 > 20) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] += 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]+2) + "_" + (curPos[3][1]));
				tmp.setBackground(col);
				curPos[3][0] += 2;
				shape = 4;
			}
			else if (shape == 4) {
				if (curPos[0][0]-1 < 1 || curPos[0][1]+1 > 10 || curPos[2][0]+1 > 20|| 
						curPos[2][1]-1 < 1 || curPos[3][1]-2 > 10) return;
				if (Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1)).getBackground() != Color.black) return;
				if (Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]-1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] -= 1;
				curPos[0][1] += 1;
				tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]+1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] += 1;
				curPos[2][1] -= 1;
				tmp = Ui.getThing(curPos[3][0] + "_" + curPos[3][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[3][0]) + "_" + (curPos[3][1]-2));
				tmp.setBackground(col);
				curPos[3][1] -= 2;
				shape = 1;
			}
		}
		if (order.get(count) == 0) {
			if (shape == 1) {
				if (curPos[1][1]-1 < 1) return;
				if (Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]-1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] -= 1;
				int tmp1 = curPos[2][0];
				int tmp2 = curPos[2][1];
				curPos[2][0] = curPos[3][0];
				curPos[2][1] = curPos[3][1];
				curPos[3][0] = tmp1;
				curPos[3][1] = tmp2;
				shape = 2;
			}
			else if (shape == 2) {
				if (curPos[1][0]-1 < 1) return;
				if (Ui.getThing((curPos[1][0]-1) + "_" + (curPos[1][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]-1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] -= 1;
				int tmp1 = curPos[0][0];
				int tmp2 = curPos[0][1];
				curPos[0][0] = curPos[2][0];
				curPos[0][1] = curPos[2][1];
				curPos[2][0] = curPos[3][0];
				curPos[2][1] = curPos[3][1];
				curPos[3][0] = tmp1;
				curPos[3][1] = tmp2;
				shape = 3;
			}
			else if (shape == 3) {
				if (curPos[1][1]+1 > 10) return;
				if (Ui.getThing((curPos[1][0]) + "_" + (curPos[1][1]+1)).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[2][0] + "_" + curPos[2][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[2][0]-1) + "_" + (curPos[2][1]+1));
				tmp.setBackground(col);
				curPos[2][0] -= 1;
				curPos[2][1] += 1;
				int tmp1 = curPos[0][0];
				int tmp2 = curPos[0][1];
				curPos[0][0] = curPos[3][0];
				curPos[0][1] = curPos[3][1];
				curPos[3][0] = tmp1;
				curPos[3][1] = tmp2;
				shape = 4;
			}
			else if (shape == 4) {
				if (curPos[1][0]+1 > 20) return;
				if (Ui.getThing((curPos[1][0]+1) + "_" + (curPos[1][1])).getBackground() != Color.black) return;
				JButton tmp = Ui.getThing(curPos[0][0] + "_" + curPos[0][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[0][0]+1) + "_" + (curPos[0][1]+1));
				tmp.setBackground(col);
				curPos[0][0] += 1;
				curPos[0][1] += 1;
				int tmp1 = curPos[2][0];
				int tmp2 = curPos[2][1];
				curPos[2][0] = curPos[0][0];
				curPos[2][1] = curPos[0][1];
				curPos[0][0] = curPos[3][0];
				curPos[0][1] = curPos[3][1];
				curPos[3][0] = tmp1;
				curPos[3][1] = tmp2;
				shape = 1;
			}
		}
	}
	public void blockLeftRight(boolean lr) {//right true left false
		Color col = blockColor.get(count);
		int start;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (lr == true) {
			start = returnRightMost();
			for (int i = 0; i < 4; i++) {
				if (curPos[i][1] == curPos[start][1]) {
					temp.add(i);
				}
			}
			for (int i = 0; i < temp.size() ; i++) {
				if (curPos[temp.get(i)][1] == 10) {
					return;
				}
				String name = curPos[temp.get(i)][0] + "_" + (curPos[temp.get(i)][1]+1);
				Color next = Ui.getThing(name).getBackground();
				if(next != Color.black) {
					return;
				}
				else {
					continue;
				}
			}
			if (curPos[start][1] == 10) {
				return;
			}
			if ( (order.get(count) == 2 && shape == 3)) {
				for (int i = 0; i < 4; i++) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]+1));
					tmp.setBackground(col);
				}
			}
			else if (start == 3 || (order.get(count) == 1 && shape == 2) || (order.get(count) == 2 && shape == 2) ||
					start == 2) {
				for (int i = 3; i > -1; i--) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]+1));
					tmp.setBackground(col);
				}
			}
			else {
				for (int i = 0; i < 4; i++) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]+1));
					tmp.setBackground(col);
				}
			}
		}
		else {
			start = returnLeftMost();
			for (int i = 0; i < 4; i++) {
				if (curPos[i][1] == curPos[start][1]) {
					temp.add(i);
				}
			}
			for (int i = 0; i < temp.size() ; i++) {
				if (curPos[temp.get(i)][1] == 1) {
					return;
				}
				String name = curPos[temp.get(i)][0] + "_" + (curPos[temp.get(i)][1]-1);
				Color next = Ui.getThing(name).getBackground();
				if(next != Color.black) {
					return;
				}
				else {
					continue;
				}
			}
			if (curPos[start][1] == 1) {
				return;
			}
			if ((order.get(count) == 1 && shape == 2) || (order.get(count) == 2 && shape == 2))  {
				for (int i = 0; i < 4; i++) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]-1));
					tmp.setBackground(col);
				}
			}
//			else if ((shape == 2) || (order.get(count) == 1)) {
//			}
			else if (start == 3) {
				for (int i = 3; i > -1; i--) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]-1));
					tmp.setBackground(col);
				}
			}
			else {
				for (int i = 0; i < 4; i++) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]-1));
					tmp.setBackground(col);
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (lr == true) curPos[i][1] += 1;
			else {
				curPos[i][1] -= 1;
			}
		}
	}
	public void dropBlock() {
		Color col = blockColor.get(count);
		if ((order.get(count) == 3 && shape == 2) || (order.get(count) == 1 && shape == 2) ||
				(order.get(count) == 2 && shape == 4) || (order.get(count) == 0 && shape == 4)) {
			for (int i = 0; i < 4; i++) {
				JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[i][0]+1) + "_" + curPos[i][1]);
				tmp.setBackground(col);
			}
		}
		else {
			for (int i = 3; i > -1; i--) {
				JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
				tmp.setBackground(Color.black);
				tmp = Ui.getThing((curPos[i][0]+1) + "_" + curPos[i][1]);
				tmp.setBackground(col);
			}
		}
		for (int i = 0; i < 4; i++) {
			curPos[i][0] += 1;
		}
	}

	public boolean isFloor() {
		int floor[] = returnFloorBlock();
		for (int i = 0; i < floor.length ; i++) {
			if (curPos[floor[i]][0] == 20) return true;
			String name = (curPos[floor[i]][0]+1) + "_" + curPos[floor[i]][1];
			Color next = Ui.getThing(name).getBackground();
			if(next.getRGB() != Color.black.getRGB()) {
				return true;
			}
			else {
				continue;
			}
		}
		floor = elseFloor();
		for (int i = 0; i < floor.length ; i++) {
			String name = (curPos[floor[i]][0]+1) + "_" + curPos[floor[i]][1];
			Color next = Ui.getThing(name).getBackground();
			if(next != Color.black) {
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
	
	public int[] returnFloorBlock() {
		ArrayList <Integer> floor = new ArrayList<Integer>();
		if (curPos[0][0] < curPos[1][0]) {
			floor.add(1);
		}
		else if (curPos[0][0] == curPos[1][0]) {
			floor.add(0);
			floor.add(1);
		}
		else {
			floor.add(0);
		}
		if (curPos[floor.get(0)][0] < curPos[2][0]) {
			floor.clear();
			floor.add(2);
		}
		else if (curPos[floor.get(0)][0] == curPos[2][0]) {
			floor.add(2);
		}
		if (curPos[floor.get(0)][0] < curPos[3][0]) {
			floor.clear();
			floor.add(3);
		}
		else if (curPos[floor.get(0)][0] == curPos[3][0]) {
			floor.add(3);
		}
		int returnFloor[] = new int [floor.size()];
		for (int i = 0; i < returnFloor.length; i++) {
			returnFloor[i] = floor.get(i);
		}
		return returnFloor;
	}
	public int[] elseFloor() {
		ArrayList <Integer> floor = new ArrayList<Integer>();

		for (int i = 0; i < 4 ; i++) {
			if (curPos[i][0] == 20) {
				continue;
			}
		String name = (curPos[i][0]+1) + "_" + curPos[i][1];
		boolean checkItself = false;
		for (int f = 0; f < 4; f++) {
			if (curPos[f][0] == curPos[i][0]+1) {
				if (curPos[f][1] == curPos[i][1]) {
					checkItself = true;
					break;
				}
			}
		}
		Color next = Ui.getThing(name).getBackground();
			if(next != Color.black && checkItself == false) {
				floor.add(i);
			}
		}
		int returnFloor[] = new int [floor.size()];
		for (int i = 0; i < returnFloor.length; i++) {
			returnFloor[i] = floor.get(i);
		}
		return returnFloor;
	}
	public int returnLeftMost() {
		if ((order.get(count) == 2 || order.get(count) == 1) && (shape == 2 || shape == 3)) {
			if (curPos[2][1] < curPos[3][1]) {
				return 2;
			}
			return 3;
		}
		if (curPos[0][1] < curPos[3][1]) {
			return 0;
		}
		return 3;
	}
	public int returnRightMost() {
		if ((order.get(count) == 0) &&(shape == 2 || shape == 4)) {
			if (curPos[0][1] < curPos[2][1]) {
				return 2;
			}
			return 0;
		}
		if ((order.get(count) == 2 || order.get(count) == 1) && (shape == 2 || shape == 3)) {
			if (curPos[2][1] < curPos[3][1]) {
				return 3;
			}
			return 2;
		}
		if (curPos[0][1] < curPos[3][1]) {
			return 3;
		}
		return 0;
	}
	public void setBlock() {
		Color col = blockColor.get(count);
		int pos = setPosition(count);
		int ord = order.get(count) + 1;
		if (ord == 7) {//I
			for (int i = 0; i < 4; i++) {
				JButton tmp = Ui.getThing((i+1) + "_" + pos);
				tmp.setBackground(col);
				curPos[i] = new int[]{i+1, pos};
			}
		}
		else if (ord == 6) {//O
			JButton tmp = Ui.getThing("1_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("1_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + (pos+1));
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos};
			curPos[1] = new int[]{1, pos+1};
			curPos[2] = new int[]{2, pos};
			curPos[3] = new int[]{2, pos+1};
		}
		else if (ord == 5) {//S
			JButton tmp = Ui.getThing("1_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + pos);
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos+1};
			curPos[1] = new int[]{2, pos+1};
			curPos[2] = new int[]{2, pos};
			curPos[3] = new int[]{3, pos};
		}
		else if (ord == 4) {//S
			JButton tmp = Ui.getThing("1_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + (pos+1));
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos};
			curPos[1] = new int[]{2, pos};
			curPos[2] = new int[]{2, pos+1};
			curPos[3] = new int[]{3, pos+1};
		}
		else if (ord == 3) {//L
			JButton tmp = Ui.getThing("1_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + (pos+1));
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos};
			curPos[1] = new int[]{2, pos};
			curPos[2] = new int[]{3, pos};
			curPos[3] = new int[]{3, pos+1};
		}
		else if (ord == 2) {//L
			JButton tmp = Ui.getThing("1_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + (pos+1));
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + pos);
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos+1};
			curPos[1] = new int[]{2, pos+1};
			curPos[2] = new int[]{3, pos+1};
			curPos[3] = new int[]{3, pos};
		}
		else if (ord == 1) {//t
			JButton tmp = Ui.getThing("1_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("3_" + pos);
			tmp.setBackground(col);
			tmp = Ui.getThing("2_" + (pos+1));
			tmp.setBackground(col);
			curPos[0] = new int[]{1, pos};
			curPos[1] = new int[]{2, pos};
			curPos[2] = new int[]{3, pos};
			curPos[3] = new int[]{2, pos+1};
		}
		setNext();
	}
	public void setNext() {
		int anC = count+1;
		if (anC == 7) {
			anC = 0;
		}
		Color col = blockColor.get(anC);
		int ord = order.get(anC) + 1;
		
		if (ord == 7) {//I
			initNext();
			JButton tmp = Ui.b11;
			tmp.setBackground(col);
			tmp = Ui.b21;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b41;
			tmp.setBackground(col);
		}
		else if (ord == 6) {//O
			initNext();
			JButton tmp = Ui.b21;
			tmp.setBackground(col);
			tmp = Ui.b22;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b32;
			tmp.setBackground(col);
		}
		else if (ord == 5) {//S
			initNext();
			JButton tmp = Ui.b22;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b32;
			tmp.setBackground(col);
			tmp = Ui.b41;
			tmp.setBackground(col);
		}
		else if (ord == 4) {//S
			initNext();
			JButton tmp = Ui.b21;
			tmp.setBackground(col);
			tmp = Ui.b32;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b42;
			tmp.setBackground(col);
		}
		else if (ord == 3) {//L
			initNext();
			JButton tmp = Ui.b21;
			tmp.setBackground(col);
			tmp = Ui.b42;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b41;
			tmp.setBackground(col);
		}
		else if (ord == 2) {//L
			initNext();
			JButton tmp = Ui.b22;
			tmp.setBackground(col);
			tmp = Ui.b41;
			tmp.setBackground(col);
			tmp = Ui.b32;
			tmp.setBackground(col);
			tmp = Ui.b42;
			tmp.setBackground(col);
		}
		else if (ord == 1) {//t
			initNext();
			JButton tmp = Ui.b21;
			tmp.setBackground(col);
			tmp = Ui.b32;
			tmp.setBackground(col);
			tmp = Ui.b31;
			tmp.setBackground(col);
			tmp = Ui.b41;
			tmp.setBackground(col);
		}
	}
	public void initNext() {
		JButton tmp = Ui.b11;
		tmp.setBackground(Color.black);
		tmp = Ui.b12;
		tmp.setBackground(Color.black);
		tmp = Ui.b21;
		tmp.setBackground(Color.black);
		tmp = Ui.b22;
		tmp.setBackground(Color.black);
		tmp = Ui.b31;
		tmp.setBackground(Color.black);
		tmp = Ui.b32;
		tmp.setBackground(Color.black);
		tmp = Ui.b41;
		tmp.setBackground(Color.black);
		tmp = Ui.b42;
		tmp.setBackground(Color.black);
	}
	public int setPosition(int num) {
		int seed;
		if (num == 7) {
			seed = 10;
		}
		else {
			seed = 9;
		}
		Random rand = new Random();
		return rand.nextInt(seed)+1;
	}
	public void initColor() {
		blockColor.add(Color.red);
		blockColor.add(Color.orange);
		blockColor.add(Color.yellow);
		blockColor.add(Color.green);
		blockColor.add(Color.blue);
		blockColor.add(Color.magenta);
		blockColor.add(Color.pink);
	}
}

