package tetris;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import tetris.Ui;

public class Main {
	public static Ui page;
	public static void main(String[] args) {
		Ui.main(args);
		block start = new block();
		start.run();
	}
	public void createBlock() {
	}
}

class block implements Runnable {
	public ArrayList<Color> blockColor = new ArrayList<Color>();
	public ArrayList<Integer> order = new ArrayList<Integer>();
	public int shape = 1;
	public int curPos[][] = new int[4][];
	public int count = 1;
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
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dropBlock();
			}
			count++;
			if (count == 7) {
				count = 1;
			}
		}
	}
	public void dropBlock() {
		Color col = blockColor.get(count);
		
		for (int i = 3; i > -1; i--) {
			JButton tmp = Ui.getThing(curPos[i][0] + "_" + curPos[i][1]);
			tmp.setBackground(Color.black);
			tmp = Ui.getThing((curPos[i][0]+1) + "_" + curPos[i][1]);
			tmp.setBackground(col);
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
			curPos[2] = new int[]{2, pos+1};
			curPos[3] = new int[]{3, pos};
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
