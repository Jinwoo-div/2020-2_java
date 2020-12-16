package tetris;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import tetris.Ui;

public class Main {
	public static Ui page;
	public static void main(String[] args) {
		Ui.main(args);
		block start = new block();
		Ui.getThing("1_1").setBackground(Color.white);
		start.run();
	}
	public void createBlock() {
	}
}

class block implements Runnable {
	public ArrayList<Color> blockColor = new ArrayList<Color>();
	public ArrayList<Integer> order = new ArrayList<Integer>();
	public int point[] = new int[3];
	public int count = 6;
	public block() {
	}
	public void run() {
		initColor();
		for(int i = 0; i < 7; i++) {
			order.add(i+1);
		}
		while (true) {
			if (count == 0) {
				Collections.shuffle(blockColor);
				Collections.shuffle(order);
			}
			setBlock(count);
			while(isFloor() == false) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dropBlock(count);
			}
		}
	}
	public void dropBlock(int num) {

		Ui.getThing("1_1").setOpaque(true);
		Ui.getThing("1_1").setBackground(Color.blue);
		
	}
	public boolean isFloor() {
		Ui.getThing(point[0] + "_" + (point[1]+1)).setOpaque(true);
		if (Ui.getThing(point[0] + "_" + (point[1]+1)).getBackground() == Color.black) {
			return false;
		}
		return true;
	}
	public void setBlock(int num) {
		Color col = blockColor.get(num);
		int pos = setPosition(num);
		int ord = order.get(num);
		point[0] = pos;
		point[2] = 1;
		if (ord == 7) {
			for (int i = 0; i < 4; i++) {
				Ui.getThing((i+1) + "_" + pos).setOpaque(true);
				Ui.getThing((i+1) + "_" + pos).setBackground(col);
			}
			point[1] = 4;
		}
		else if (ord == 6) {
			Ui.getThing("1_" + pos).setOpaque(true);
			Ui.getThing("1_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + pos).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("1_" + pos).setBackground(col);
			Ui.getThing("1_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + pos).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			point[1] = 2;
		}
		else if (ord == 5) {
			Ui.getThing("1_" + pos).setOpaque(true);
			Ui.getThing("1_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + (pos+2)).setOpaque(true);
			Ui.getThing("1_" + pos).setBackground(col);
			Ui.getThing("1_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + (pos+2)).setBackground(col);
			point[1] = 2;
		}
		else if (ord == 4) {
			Ui.getThing("1_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + pos).setOpaque(true);
			Ui.getThing("3_" + pos).setOpaque(true);
			Ui.getThing("1_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + pos).setBackground(col);
			Ui.getThing("3_" + pos).setBackground(col);
			point[1] = 3;
		}
		else if (ord == 3) {
			Ui.getThing("1_" + pos).setOpaque(true);
			Ui.getThing("2_" + pos).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("3_" + (pos+1)).setOpaque(true);
			Ui.getThing("1_" + pos).setBackground(col);
			Ui.getThing("2_" + pos).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			Ui.getThing("3_" + (pos+1)).setBackground(col);
			point[1] = 3;
		}
		else if (ord == 2) {
			Ui.getThing("1_" + (pos+1)).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("3_" + (pos+1)).setOpaque(true);
			Ui.getThing("3_" + pos).setOpaque(true);
			Ui.getThing("1_" + (pos+1)).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			Ui.getThing("3_" + (pos+1)).setBackground(col);
			Ui.getThing("3_" + pos).setBackground(col);
			point[1] = 3;
		}
		else if (ord == 1) {
			Ui.getThing("1_" + pos).setOpaque(true);
			Ui.getThing("2_" + pos).setOpaque(true);
			Ui.getThing("3_" + pos).setOpaque(true);
			Ui.getThing("2_" + (pos+1)).setOpaque(true);
			Ui.getThing("1_" + pos).setBackground(col);
			Ui.getThing("2_" + pos).setBackground(col);
			Ui.getThing("3_" + pos).setBackground(col);
			Ui.getThing("2_" + (pos+1)).setBackground(col);
			point[1] = 3;
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
