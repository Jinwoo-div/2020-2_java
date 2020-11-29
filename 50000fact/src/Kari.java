
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Kari {
	public static void main(String[] args) throws IOException {
		Factorial a = new Factorial(50000);
		String result = a.getCaculate();
		exportFile(result);
	}
	static void exportFile(String result) throws IOException{
		BufferedOutputStream bs = null;
		try{
			bs = new BufferedOutputStream(new FileOutputStream("C:/homeWork/result.txt"));
			bs.write(result.getBytes());
	}	catch (Exception e) {
		e.getStackTrace();
		}
		finally {
			bs.close();
		}
	}
}

class Factorial2 {
	private int key;
	private int carry;
	private String sum;
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<Integer> starSum = new ArrayList<Integer>();
	private ArrayList<Integer> allNum = new ArrayList<Integer>();
	private ArrayList <int[]> aList = new ArrayList<int[]>();
	
	public Factorial2(int key) {
		this.key = key;
		sum = Integer.toString(this.key);
		for (int i = 1; i < key + 1; i++) {
			allNum.add(i);
		}
		int k = key - 1;
		for (int i = 0; i < key/2; i++) {
			allNum.set(i, allNum.get(i) * allNum.get(k));
			allNum.remove(k);
			k--;
		}
		for (int i = 0; i < key/2; i++) {
			String tmpNum = allNum.get(i).toString();
			int arr[] = new int[tmpNum.length()];
			for (int j = 0 ; j < tmpNum.length(); j++) {
				arr[j] = tmpNum.charAt(j) - '0';
			}
			aList.add(arr);
		}
	}

	public String getCaculate() {
		int count = 1;
		int zeroCount = 0;
		for (int i = 0; i < aList.get(0).length ; i++) {
			num.add(aList.get(0)[i]);
		}
		int s = allNum.size();
		for (int i = 0; i < s; i++) {
			while(true) {
				int last = num.size() - 1;
				if (num.lastIndexOf(0) != last) {
					break;
				}
				zeroCount++;
				num.remove(last);
			}
			starSum.clear();
			for (int j = 0; j < num.size(); j++) {
				starSum.add(num.get(j));
			}
			int t = 0;
			int q = 0;
			for (int d = aList.get(i).length -1; d > -1; d--) {
				int p = 1;
				for (int w = d; w > 0; w--) {
					p *= 10;
				}
				t += aList.get(i+1)[q] * p; 
				q++;
			}
			for (int j = 1 ; j < t; j++) {
				for (int k = num.size() - 1; k > -1; k--) {
					while(starSum.size() < k + 1) {
						starSum.add(0, 0);
					}
					int tmpsum = num.get(k) + starSum.get(k);
					if (carry == 1) {
						tmpsum += 1;//carry 있음 추가로 더해줌
					}
					if (tmpsum > 9) {// carry 발생
						
						tmpsum -= 10; // 남길수 만들기
						carry = 1;
					}
					else {
						carry = 0;
					}
					num.set(k, tmpsum);
					if (carry == 1 && k == 0) {
						num.add(0, carry);
						carry = 0;
					}
				}
			}
			System.out.println(count++);
		}
		String result = "";
		for (int i = 0; i < num.size(); i++) {
			result += num.get(i).toString();
		}
		return result;
	}
}