package kr.ac.inha;

import java.util.ArrayList;
import java.util.LinkedList;

public class Source6 {
public static void main(String[] args) {
	ArrayList<Integer> al = new ArrayList<>();
	LinkedList<Integer> ll = new LinkedList<>();
	
	Long start = System.nanoTime();
	for (int i = 0; i < 10000; i++) 
		al.add(0, i);
	Long end = System.nanoTime();
	Long duration = end - start;
	System.out.println("ArrayList로 처리한 시간 : " + duration);
	
	start = System.nanoTime();
	for(int i = 0; i < 10000; i++)
		ll.addFirst(i);
	end = System.nanoTime();
	duration = end - start;
	System.out.println("LinkedList로 처리한 시간 :" + duration);
	
	//get 연산 비교

	start = System.nanoTime();
	for (int i = 0; i < 10000; i++) 
		al.get(i);
	end = System.nanoTime();
	duration = end - start;
	System.out.println("ArrayList로 처리한 시간 : " + duration);
	
	start = System.nanoTime();
	for(int i = 0; i < 10000; i++)
		ll.get(i);
	end = System.nanoTime();
	duration = end - start;
	System.out.println("LinkedList로 처리한 시간 :" + duration);
	
	
}
}
