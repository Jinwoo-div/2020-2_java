package Caculator;

import java.util.Stack;
import java.util.LinkedList;
import javax.swing.JOptionPane;

class Caculate {
	int point = 0;
	Stack<String> caculation = new Stack<String>();
	LinkedList<String> caculateList;
	char[] caculateArr;
	Caculate(String Str) {
		caculateList = new LinkedList<String>();
		caculateArr = Str.toCharArray();
		for (int i = 0; i < caculateArr.length ; i++) {
			caculateList.add(Character.toString(caculateArr[i]));
		}
		makeCaculation(caculateList);
	}
	void makeCaculation(LinkedList<String> list) {
		int i = 0;
		while(true) {
			if (rankOfVal(list.get(i)) == 2) point = i;
			if (list.get(i).equals(")")) {
				valCaculate(point + 1, i - 1);
				for (int p = i; p >= point; p--) {
					list.remove(point);
				}
				list.add(point, Integer.toString(finalCaculate()));
				caculateList = list;
			}
			String a = ")";
			if (list.contains(a) == false) {
				break;
			}
			i++;
			}
		valCaculate(0, list.size() - 1);
	}
	int finalCaculate() {
		int num = 0;
		while (caculation.isEmpty() == false) {
			String front = caculation.pop();
			if (caculation.isEmpty()) return num += Integer.parseInt(front);
			String rear = caculation.pop();
			if (rear.equals("+")) {
				num += Integer.parseInt(front) +
						Integer.parseInt(caculation.pop());
			}
			else {
				num += Integer.parseInt(front) -
						Integer.parseInt(caculation.pop());
				
			}
		}
		return num;
	}
	
	void valCaculate(int start, int end) {
		for (;start < end + 1; start++) {
			if (rankOfVal(caculateList.get(start)) == 1)	{
				if (caculateArr[start] == '*') {
					int temp;
					temp = Integer.parseInt(caculateList.get(start - 1)) * 
							Integer.parseInt(caculateList.get(start + 1));
					caculation.push(Integer.toString(temp));
					start++;
				}
				else if (caculateArr[start] == '%') {
					int temp;
					temp = Integer.parseInt(caculateList.get(start - 1)) % 
							Integer.parseInt(caculateList.get(start + 1));
					caculation.push(Integer.toString(temp));
				}
				else {
					int temp;
					temp = Integer.parseInt(caculateList.get(start - 1)) /
							Integer.parseInt(caculateList.get(start + 1));
					caculation.push(Integer.toString(temp));
				}
			}
			else if (rankOfVal(caculateList.get(start)) == -1) {
				if (caculateList.size() <= start+1) {
					caculation.push(caculateList.get(start));
					break;
				}
				if (rankOfVal(caculateList.get(start + 1)) != 1) caculation.push(caculateList.get(start));
			}
			else {
				caculation.push(caculateList.get(start));
				}
			}
		}
	
	int rankOfVal(String val) {
   		if (val.equals("+")|| val.equals("-")) {
 			return 0;
		}
		else if (val.equals("/") || val.equals("*") || val.equals("%")) {
			return 1;
		}
		else if (val.equals("(")) {
			return 2;
		}
		return -1;
		
	}
}

public class Main {
	public static void main(String[] args) {
		while(true) {
			String Str = JOptionPane.showInputDialog("계산식 입력");
			if (Str.equals("exit")) {
				System.out.println("a");
				break;
			}
			Caculate Caculation = new Caculate(Str);
			System.out.println(Caculation.finalCaculate());
		}
		
	}
}
