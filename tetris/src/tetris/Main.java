package tetris;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import tetris.Ui;

public class Main {
	public static block start;
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
		start.run();
	}
}

class block implements Runnable {
	public ArrayList<Color> blockColor = new ArrayList<Color>();
	public ArrayList<Integer> order = new ArrayList<Integer>();
	public int shape = 1;
	public int curPos[][] = new int[4][];
	public int count = 1;
	public boolean movingLeft = false;
	public boolean movingRight = false;
	public boolean rotate = false;
	public boolean down = false;
	public block() {
	}
	public void run() {
		initColor();
		for(int i = 0; i < 7; i++) {
			order.add(i+1);
		}
		while (true) {
			if (count == 1) {
				Collections.shuffle(blockColor);
				Collections.shuffle(order);
			}
			setBlock();
			while(isFloor() == false) {
				try {
					Thread.sleep(500);
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
			}
			count++;
			shape = 1;
			if (count == 7) {
				count = 1;
			}
		}
	}
	public void rotateBlock() {
		Color col = blockColor.get(count);
		if (order.get(count) == 7) {
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
		if (order.get(count) == 4) {
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
		if (order.get(count) == 5) {
			if (shape == 1) {
				if (curPos[0][0]+1 > 20 || curPos[0][1]-1 < 1 || curPos[2][0]+1 > 20 || 
						curPos[2][1]+1 > 10 || curPos[3][1]+2 > 20) return;
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
		if (order.get(count) == 3) {
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
		if (order.get(count) == 2) {
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
			if (start == 3 || (order.get(count) == 2 && shape == 2) || (order.get(count) == 3 && shape == 2)) {
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
			if ((order.get(count) == 2 && shape == 2) || (order.get(count) == 3 && shape == 2))  {
				for (int i = 0; i < 4; i++) {
					JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
					tmp.setBackground(Color.black);
					tmp = Ui.getThing(curPos[i][0] + "_" + (curPos[i][1]-1));
					tmp.setBackground(col);
				}
			}
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
		if ((order.get(count) == 4 && shape == 2) || (order.get(count) == 2 && shape == 2) ||
				(order.get(count) == 3 && shape == 4)) {
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
			if(next != Color.black) {
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
		if ((order.get(count) == 3 || order.get(count) == 2) && (shape == 2 || shape == 3)) {
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
		if ((order.get(count) == 3 || order.get(count) == 2) && (shape == 2 || shape == 3)) {
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
		int ord = order.get(count);
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
		rand.setSeed(System.currentTimeMillis());
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

