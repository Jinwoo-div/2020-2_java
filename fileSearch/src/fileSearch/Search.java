package fileSearch;

import java.io.File;
import java.util.Scanner;

public class Search {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();//경로 입력
		searchFile(path);
		in.close();
	}
	static void searchFile(String path) {
		File search = null;
		try{
			search = new File(path);//파일 객체생성
		} catch (Exception e) {
		}
		File[] li = search.listFiles();
		for (File fil: li) {//경로내 파일 순회
			if (fil.isDirectory()) {//만약 파일이 폴더일 경우
				String[] downList = fil.list();//헤당 폴더내 파일 이름 리스트 생성
				System.out.println("-- " + fil.getName() + " --");
				for(String fi : downList) {
					System.out.printf(fi + " ");//리스트 출력
				}
				System.out.printf("%n---------%n");
			}
			else {//폴더가 아닐경우
				System.out.println(fil.getName());// 파일 이름 출력
			}
		}
	}
}

