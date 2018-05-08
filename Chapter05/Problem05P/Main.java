import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static boolean isPossibleName(String fullName, int N, String[] words) {
		for (int i = 0; i < N; i += 1) {
			String prefix = words[i];
			if (fullName.startsWith(prefix) == false) {
				continue;
			}

			for (int k = 0; k < N; k += 1) {
				String suffix = words[k];
				if (i == k
						|| fullName.endsWith(suffix) == false)
				{
					continue;
				}

				for (int j = 0; j < N; j += 1) {
					String middle = words[j];
					if( i == j || j == k )
					{
						continue;
					}

					String concatenated = prefix + middle + suffix;
					if(fullName.equals(concatenated)){
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void testCase(int caseIndex) {
		int N = scanner.nextInt();
		String fullName = scanner.next();
		String[] words = new String[N];
		for (int i = 0; i < N; i += 1) {
			words[i] = scanner.next();
		}

		boolean possible = isPossibleName(fullName, N, words);

		if(possible){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}
