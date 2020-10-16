package kr.ac.inha;

public class Source2 {
	public static void main (String[] args) {
		double interets[][] = {{1.0, 1.0,3.0 , 3.1},{1.0, 1.0,3.0 , 3.1},{1.0, 1.0,3.0 , 3.1},{1.0, 1.0,3.0 , 3.1}};
		double sum1[] = {0.0, 0.0, 0.0, 0.0};
		double sum2 = 0.0;
		for (int i = 0; i < interets.length; i++) {
			for (int j = 0; j < interets[i].length; j++) {
				sum1[i] += interets[i][j];
			}
			System.out.printf("%d 차년도 평균 이자율 = %.2f%%\n", i + 1, sum1[i]/4);
			sum2+=sum1[i];
		}
		System.out.printf("3년간 평균 이자율= %.2f%%\n", sum2/3);
	}
}
