import java.io.*;
import java.util.*;
import java.lang.*;

class Main {
	public static final Scanner scanner = new Scanner(System.in);
	/**
	 * N번째 피보나치 수열의 원소를 출력하는 함수
	 * 1, 2번째 숫자는 1이고, 다른 원소는 이전의 두 원소의 합이다
	 */
	public static int fib(int n){
		if(n == 1 || n == 2) return 1;		// 1혹은 2일때는 1로 자명하다
		else return fib(n-1) + fib(n-2);	// 3이상일 때 에는 앞선 두 항의 합을 재귀적으로 반환한다
	}
	
	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();
		int result = fib(n);
		System.out.println(result);
	}
}