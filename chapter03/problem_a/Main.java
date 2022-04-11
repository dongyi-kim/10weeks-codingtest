import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_TABLE_LENGTH = 10000;

	/**
	 * data[0] ~ data[n-1]에 등장한 번호들에 대한 빈도수 테이블을 채우는 함수
	 * @param data
	 * @param n
	 * @param table  table[x] := data배열에서 x가 등장한 횟수
	 */
	public static void fillFrequencyTable(int[] data, int n, int[] table) {
		//table배열을 모두 0으로 초기화한다.
		//배열이 항상 0으로 초기화되어있다고 보장할 수 있다면, 생략해도 된다.
		for (int i = 0; i < table.length; i += 1) {
			table[i] = 0;
		}


		for (int i = 0; i < n; i += 1)
		{ //각 원소 data[i]에 대해 차례로 한 번씩
			int index = data[i];

			//data[i]의 값과 대응되는 배열의 원소 값을 1 증가시킨다.
			//즉, table[x]는 data[0] ~ data[n-1]에서 x가 등장한 횟수 만큼 등장한다.
			table[index] += 1;
		}
	}


	/**
	 * data[0] ~ data[n-1]사이에서 가장 많이 등장한 번호를 반환하는 함수
	 * @param data
	 * @param n
	 * @return  가장 많이 등장한 번호. 여러개인 경우 사전순으로 가장 빠른 번호.
	 */
	public static int getFrequentNumber(int[] data, int n) {
		int frequent_number = 0;  //0000~9999중  가장 많이 등장한 번호

		int[] table = new int[MAX_TABLE_LENGTH];
		fillFrequencyTable(data, n, table);

		//방법 1 : 가능한 모든 번호에 대하여 조사
		for(int number = 0000; number <= 9999; number += 1)
		{ // 0000 ~ 9999사이의 모든 숫자  number에 대하여
			if( table[number] > table[frequent_number] )
			{
				frequent_number = number;
			}
		}

//		//방법 2 : 등장한 적 있는 모든 원소에 대하여 조사
//		for(int i = 0 ; i < n ; i++)
//		{ // 등장한 적 있는 모든 숫자 dataa[i]에 대하여
//			if( table[data[i]] > table[frequent_number])
//			{ //현재까지 찾은 숫자보다 더 많이 등장한 숫자를 찾았거나,
//				frequent_number = data[i];
//			}else if(table[data[i]] == table[frequent_number] && frequent_number > data[i])
//			{ //현재까지 찾은 숫자와 등장 횟수는 같지만 더 작은(사전순으로 빠른)숫자를 찾은 경우
//				frequent_number = data[i];
//			}
//		}

		return frequent_number;
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = scanner.nextInt();
		}

		int answer = getFrequentNumber(data, n);

		System.out.printf("%04d", answer);
	}
}
