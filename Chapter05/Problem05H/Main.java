import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		TreeSet<Integer> integers = new TreeSet<>();

		for(int i = 0 ; i < N ; i++){
			// integers := 이전까지 등장한 모든 정수를 저장한 집합
			int X = scanner.nextInt();

			if(integers.contains(X)){
				// X와 같은 정수가 입력된 적 있다면
				writer.write("DUPLICATED\n");
			}else{
				// X와 같은 정수가 입력된 적 없다면 추가한다.
				integers.add(X);
				writer.write("OK\n");
			}
		}

		writer.flush();
		writer.close();
	}

}
