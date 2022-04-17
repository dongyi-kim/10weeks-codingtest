import java.io.*;
import java.util.*;
import java.lang.*;

class Utility {
	/**
	 * (재귀함수 버전 구현체)
	 * arr[l] ~ arr[r] 사이에 target이 존재하는지 반환하는 함수
	 *
	 * @param arr    오름차순으로 정렬된 배열
	 * @param target 탐색하고자 하는 값
	 * @param l      탐색범위의 왼쪽 끝 인덱스
	 * @param r      탐색범위의 오른쪽 끝 인덱스
	 * @return arr내에 target이 존재하면 true, 그렇지 않으면 false
	 */
	public static boolean binarySearch(int[] arr, int target, int l, int r) {
		if (l > r) return false;
		else if (l == r) return arr[l] == target;
		
		int m_idx = (l + r) / 2;
		int median = arr[m_idx];
		if (target <= median) {
			return binarySearch(arr, target, l, m_idx);
		} else {
			return binarySearch(arr, target, m_idx + 1, r);
		}
	}
	
	/**
	 * (반복문 버전 구현체)
	 * arr[l] ~ arr[r] 사이에 target이 존재하는지 반환하는 함수
	 *
	 * @param arr    오름차순으로 정렬된 배열
	 * @param target 탐색하고자 하는 값
	 * @return arr내에 target이 존재하면 true, 그렇지 않으면 false
	 */
	public static boolean binarySearch(int[] arr, int target) {
		int left = 0;       // 현재 탐색 범위의 왼쪽끝 인덱스
		int right = arr.length - 1; // 현재 탐색 범위의 오른쪽끝 인덱스
		
		while (left < right) {  // 현재 탐색 범위가 두 개 이상의 원소를 포함하는 경우
			int m_idx = (left + right) / 2;
			int median = arr[m_idx];
			if (target <= median) {
				right = m_idx;  // 오른쪽 탐색 범위를 중앙값 이전으로 좁힌다
			} else {
				left = m_idx + 1;   // 왼쪽 탐색 범위를 중앙값 후로 좁힌다
			}
		}
		return arr[left] == target;
	}
	
}

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[]{1, 3, 7, 8, 9, 20, 30, 50, 70, 120};
		int target = 120;
		//Arrays.sort(arr);
		
		
		boolean result = Utility.binarySearch(arr, target, 0, arr.length - 1); // 구현한 함수의 결과
		
		if (result) {
			System.out.println("배열 내에 존재합니다");
		} else {
			System.out.println("배열 내에 존재하지 않습니다");
		}
		
	}
}