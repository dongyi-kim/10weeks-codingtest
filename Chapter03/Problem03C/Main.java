import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static final int MAX_SERIAL_NUMBER = 100000;

	public static void fillFrequencyTable(int[] data, int n, int[] table) {
		for (int i = 0; i < table.length; i += 1) {
			table[i] = 0;
		}
		for (int i = 0; i < n; i += 1) {
			int index = data[i];
			table[index] += 1;
		}
	}

	/**
	 * data[0] ~ data[n-1]에서 중복이 존재하지 않는 원소들을 반환한다.
	 * 단, 각 원소들은 오름차순으로 정렬되어 있어야 한다.
	 * @param data  data[0] ~ data[n-1]에는 10만 이하의 자연수다.
	 * @param n
	 * @return
	 */
	public static ArrayList<Integer> getUniqueElements(int[] data, int n)
	{
		ArrayList<Integer> ret = new ArrayList<>();

    	//data에 대한 빈도수 테이블을 계산한다
		int[] table = new int [MAX_SERIAL_NUMBER+1];
		fillFrequencyTable(data, n, table);
		
		for(int number = 1; number <= MAX_SERIAL_NUMBER; number += 1)
		//or for(int number : data)
		{ //존재할 수 있는 모든 시리얼 넘버에 대해 차례로 조회한다.

			//전체 데이터에서 한 번만 등장한 number를 차례로 정답 리스트에 추가한다.
			if(table[number] == 1)
			{
				ret.add(number);
			}
		}

		//오름차순 순서로 추가했기 때문에 ret에 대한 정렬은 불필요하다.
		return ret;
	}



//	public static ArrayList<Integer> getUniqueElements(int[] data, int n)
//	{ //  <algorithm2> 정렬을 사용한 방법
//		ArrayList<Integer> ret = new ArrayList<>();
//
//		//data를 오름차순으로 정렬한다.
//		//정렬을 하면, 같은 값을 가진 원소는 항상 인접해있다.
//		Arrays.sort(data);
//
//		for(int i = 0 ; i < n ; i ++)
//		{ //모든 원소 data[i]를 오름차순으로 한 번씩 조회한다.
//
//			if( (i == 0 || data[i-1] != data[i]) //가장 왼쪽의 원소이거나, 왼쪽의 원소와 다르고
//						&& ( i == n-1 ||  data[i+1] != data[i])) //가장 오른쪽의 원소이거나, 가장 오른쪽의 원소와 다르다면
//			{ //중복이 없는 원소이므로 정답에 추가한다.
//				ret.add(data[i]);
//			}
//		}
//
//		//오름차순 순서로 추가했기 때문에 ret에 대한 정렬은 불필요하다.
//
//		return ret;
//	}

	public static void main(String[] args) throws Exception {
		//이 문제는 출력할 데이터가 많기때문에 BufferedWriter를 사용하여 출력해야 한다.
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

		//N개의 시리얼 번호를 차례로 입력 받는다
		int n = scanner.nextInt();
		int[] data = new int[n];
		for(int i = 0 ; i < n ; i ++)
		{
			data[i] = scanner.nextInt();
		}

		//중복없는 원소들을 계산한다.
		ArrayList<Integer> answers = getUniqueElements(data, n);

		//각 원소들을 출력한다.
		for(int i = 0 ; i < answers.size() ; i++)
		{
			int element = answers.get(i);
			if( i > 0 )
			{ //첫 번째 원소가 아니라면 앞에 공백을 하나 추가한다.
				writer.write(" ");
			}
			writer.write(String.valueOf(element));
		}

		//BufferedWriter를 비우고 해제한다.
		writer.flush();
		writer.close();
	}

}
