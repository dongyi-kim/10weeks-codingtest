import java.io.*;
import java.lang.*;
import java.util.*;

class SortingAlgorithm {

	/**
	 * @brief arr[left] ~ arr[right] 범위에서 원소 하나를 피벗으로 선택하는 함수
	 *
	 * @param arr
	 * @param left
	 * @param right
	 * @return 피봇으로 선정된 원소의 인덱스를 반환한다
	 */
	public static int getPivotIndex(int[] arr, int left, int right) {
		int mid = (left + right) / 2;

		// a ≤ b ≤ c 또는 c ≤ b ≤ a
		if ((arr[left] <= arr[mid] && arr[mid] <= arr[right]) ||
				(arr[right] <= arr[mid] && arr[mid] <= arr[left]))
			return mid;

		// b ≤ a ≤ c 또는 c ≤ a ≤ b
		if ((arr[mid] <= arr[left] && arr[left] <= arr[right]) ||
				(arr[right] <= arr[left] && arr[left] <= arr[mid]))
			return left;

		// 나머지는 right 가 중앙값
		return right;
	}

	/**
	 * @brief 배열의 두 원소 arr[indexA] 와 arr[indexB]를 swap해주는 함수
	 *
	 * @param arr
	 * @param indexA
	 * @param indexB
	 */
	public static void swap(int[] arr, int indexA, int indexB) {
		int temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}

	/**
	 * @brief quickSort 함수의 축약형
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * @brief arr[left] ~ arr[right] 범위를 정렬해주는 함수
	 *
	 * @param arr	정렬할 배열
	 * @param left	정렬 범위의 왼쪽 인덱스
	 * @param right	정렬 범위의 오른쪽 인덱스
	 */
	public static void quickSort(int[] arr, int left, int right) {
		if (left >= right) { // 원소가 하나 이하라면 정렬된 배열이다
			return;
		}
		int pivot = arr[getPivotIndex(arr, left, right)]; // 전체 범위를 나눌 피벗을 찾는다

		int lp = left; // 피벗 그룹의 가장 왼쪽 인덱스
		int i = left;  // 반복자
		int rp = right; // 피벗 그룹의 가장 오른쪽 인덱스

		while (i <= rp) {
			if (arr[i] < pivot){	// 현재 값이 피벗보다 작다면
				swap(arr, lp, i);	// 자리를 바꿔 왼쪽부터 채워넣는다
				lp += 1;
				i += 1;
			} else if (pivot < arr[i]){ // 현재 값이 피벗보다 크다면
				swap(arr, i, rp);		// 자리를 바꿔 오른쪽부터 채워넣는다
				rp -= 1;
			} else{		// 피벗과 같은 숫자 발견
				i += 1;	// 그냥 이동만 한다
			}
		}

		quickSort(arr, left, lp - 1); // 피벗 기준 왼쪽 범위 정렬
		quickSort(arr, rp + 1, right); // 피벗 기준 오른쪽 범위 정렬
	}

}

public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i += 1) {
			arr[i] = scanner.nextInt();
		}

		SortingAlgorithm.quickSort(arr);

		for (int i = 0; i < N; i += 1) {
			if (i > 0) {
				writer.write(" ");
			}
			writer.write(String.valueOf(arr[i]));
		}

		writer.flush();
		writer.close();
	}

}
