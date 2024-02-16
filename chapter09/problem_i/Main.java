import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

	/**
	 * 배열으로 주어진 행렬이 모두 곱하기가 가능한지 판단하는 함수
	 *
	 * @param matrices
	 * @return			모두 곱하기가 가능하다면 true, 그렇지 않다면 false를 반환한다
	 */
	public static boolean isMultiplicable(Matrix[] matrices){
		boolean multiplicable = true;
		for(int i = 0; i + 1 < matrices.length; i += 1){
			if(matrices[i].columnSize != matrices[i+1].rowSize){
				multiplicable = false;
				break;
			}
		}
		return multiplicable;
	}

	public static void testCase(int caseIndex) {
		int n = scanner.nextInt();
		Matrix[] matrices = new Matrix[n];

		for (int i = 0; i < n; i += 1) {
			int r = scanner.nextInt();
			int c = scanner.nextInt();
			matrices[i] = new Matrix(r, c);
		}


		boolean multiplicable = isMultiplicable(matrices);
		if(false == multiplicable){
			System.out.println("-1");
			return;
		}


		Solution solution = new Solution(matrices);
		int ansnwer = solution.f(0, n-1);
		System.out.println(ansnwer);
	}

}


class Solution{
	private static final int EMPTY = -1;
	private static final int INFINITY = 1_000_000_000;

	private Matrix[] matrices;
	private int[][] memo;
	private int n;

	public Solution(Matrix[] matrices){
		this.matrices = matrices.clone();
		this.n = this.matrices.length;
		this.memo = new int[n][n];
		for(int i = 0 ; i < n ; i += 1){
			Arrays.fill(memo[i], EMPTY);
		}
	}


	/**
	 * matrices[leftMost...rightMost] 범위의 모든 행렬을 곱할때 발생하는 최소의 곱하기 연산의 수
	 *
	 * @param leftMost		가장 왼쪽 끝 인덱스
	 * @param rightMost		가장 오른쪽 끝 인덱스
	 * @return
	 */
	public int f(int leftMost, int rightMost){
		if(leftMost < 0 || rightMost >= n || leftMost > rightMost ){
			return INFINITY;
		}else if( memo[leftMost][rightMost] != EMPTY){
			return memo[leftMost][rightMost];
		}else if(leftMost == rightMost){
			return 0;
		}

		int answer = INFINITY;

		// [leftMost, mid] 와 [mid+1, rightMost]로 나누었을때
		for(int mid = leftMost; mid < rightMost; mid += 1){

			// 좌우 영역을 모두 곱하는데 소모되는 비용
			int leftCost =  f(leftMost, mid);
			int rightCost = f(mid+1,rightMost);

			// 좌우 결과 행렬을 곱하는데 소요되는 비용
			int concatCost =  matrices[leftMost].rowSize
							* matrices[mid].columnSize
							* matrices[rightMost].columnSize;

			// 전체 비용
			int totalCost = leftCost + rightCost + concatCost;

			answer = Math.min(answer, totalCost);
		}

		memo[leftMost][rightMost] = answer;
		return answer;
	}
}

class Matrix {
	public final int rowSize;
	public final int columnSize;

	public Matrix(int rowSize, int columnSize) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
	}
}
