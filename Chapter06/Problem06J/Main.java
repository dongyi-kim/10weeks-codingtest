import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		// 각 <정수, 빈도수> 형태로 key-value를 저장할 Map 자료구조
		TreeMap<Integer, Integer> frequencyMap = new TreeMap<>();

		for(int i = 0 ; i < N; i+= 1){
			// frequencyMap := 이전에 입력된 정수들의 빈도수를 저장하고 있다.
			int X = scanner.nextInt();

			// 한 번도 등장한 적 없는 숫자라면
			if(frequencyMap.containsKey(X) == false){
				// 빈도수를 0으로 초기화한다.
				frequencyMap.put(X, 0);
			}

			// 현재 frequencyMap에 저장된 적 있는 숫자의 갯수를 저장한다.
			int c = frequencyMap.size();

			// X가 등장한 빈도수를 갱신하여 저장한다.
			int f = frequencyMap.get(X) + 1;
			frequencyMap.put(X, f);

			// 정보를 출력한다.
			writer.write(String.format("%d %d\n", c, f));

		}

		writer.flush();
		writer.close();
	}

}