
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
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

class Factorial {
	private int key;
	private int carry;
	private String sum;
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<Integer> starSum = new ArrayList<Integer>();
	
	public Factorial(int key) {
		this.key = key;
		sum = Integer.toString(this.key);
	}

	public String getCaculate() {
		int count = 1;
		for (int i = 0; i < sum.length(); i++) {
			num.add(Character.getNumericValue(sum.charAt(i)));
		}
		for (int i = key; i > 1; i--) {
			starSum.clear();
			for (int j = 0; j < num.size(); j++) {
				starSum.add(num.get(j));
			}
			for (int j = 1; j < i - 1; j++) {
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