import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int[][] nextImage(int r, int c) {
		int[][] img = new int[r][c];
		for (int i = 0; i < r; i += 1) {
			for (int j = 0; j < c; j += 1) {
				img[i][j] = scanner.nextInt();
			}
		}
		return img;
	}

	public static void main(String[] args) {
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int[][] imgA = nextImage(r, c);
		int[][] imgB = nextImage(r, c);


		Quilting quilting = new Quilting(r, c, imgA, imgB);

		int answer = quilting.f(r - 1, c - 1);

		System.out.println(answer);
	}
}

class Quilting {
	private static int EMPTY = -1;
	private static int INFINITY = 1000000000;

	private int[][] imageA;
	private int[][] imageB;
	private int[][] memo;
	private int r;
	private int c;

	public Quilting(int r, int c, int[][] imageA, int[][] imageB) {
		this.r = r;
		this.c = c;
		this.memo = new int[r][c];
		this.imageA = new int[r][c];
		this.imageB = new int[r][c];
		for (int i = 0; i < r; i += 1) {
			for (int j = 0; j < c; j += 1) {
				memo[i][j] = EMPTY;
				this.imageA[i][j] = imageA[i][j];
				this.imageB[i][j] = imageB[i][j];
			}
		}
	}

	/**
	 * 현재 0번째 행부터 이어온 경계선의 마지막이 (lastRow, lastCol)일 때 최소의 부자연스러움 수치를 계산하는 함수
	 *
	 * @param lastRow 경계선 마지막 행
	 * @param lastCol 경계선 마지막 열
	 * @return 이때의 최소 부자연스러움
	 */
	public int f(int lastRow, int lastCol) {
		if (lastRow < 0 || lastCol < 0 || lastCol >= c) {
			return INFINITY;
		} else if (memo[lastRow][lastCol] != EMPTY) {
			return memo[lastRow][lastCol];
		} else if (lastRow == 0) {
			// 마지막 행인 경우 해당 칸의 픽셀 차이만 비교한다
			int diff = imageA[lastRow][lastCol] - imageB[lastRow][lastCol];
			return diff * diff;
		}

		// 일단 현재 픽셀의 차이값을 계산한다
		int diff = imageA[lastRow][lastCol] - imageB[lastRow][lastCol];
		int error = diff * diff;

		// 이전 행 까지의 세가지 경우의 수중 최적해와 더한다
		int answer = error + MIN(
				f(lastRow - 1, lastCol - 1),
				f(lastRow - 1, lastCol),
				f(lastRow - 1, lastCol + 1)
		);

		memo[lastRow][lastCol] = answer;
		return answer;
	}


	public static int MIN(int... arr) {
		int min = arr[0];
		for (int i = 0; i < arr.length; i += 1) {
			min = Math.min(min, arr[i]);
		}
		return min;
	}
}

