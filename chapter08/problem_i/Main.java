import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAX_NUMBER = 10000;

	public static final boolean isPrime[] = new boolean[MAX_NUMBER + 1];

	/**
	 * "number"에서 한 글자를 변경해 만들 수 있는 모든 1000~9999사이의 소수를 반환해주는 함수
	 *
	 * @param number
	 * @return
	 */
	public static ArrayList<Integer> getAdjacentPrimeNumbers(int number){
		ArrayList<Integer> adjacentPrimeNumbers = new ArrayList<>();

		int[] orgDigits = new int[4];
		orgDigits[0] = number / 1000;
		orgDigits[1] = (number % 1000) / 100;
		orgDigits[2] = (number % 100) / 10;
		orgDigits[3] = (number % 10);

		for(int pos = 0 ; pos < 4 ; pos += 1){
			int[] newDigits = orgDigits.clone();
			for(int digit = 0 ; digit <= 9; digit += 1){
				newDigits[pos] = digit;

				int newInteger = newDigits[0] * 1000 + newDigits[1] * 100 + newDigits[2] * 10 + newDigits[3];
				if(1000 <= newInteger && newInteger <= 9999 && isPrime[newInteger] == true && newInteger != number){
					adjacentPrimeNumbers.add(newInteger);
				}
			}
		}

		return adjacentPrimeNumbers;
	}

	public static void testCase(int caseIndex) {
		int origin = scanner.nextInt();
		int dest = scanner.nextInt();

		boolean[] visited = new boolean[MAX_NUMBER];
		int[] distance = new int[MAX_NUMBER];

		Queue<State> bfsQueue = new LinkedList<>();
		State initialState = new State( origin, 1 );
		bfsQueue.add(initialState);

		while(!bfsQueue.isEmpty()){
			State current = bfsQueue.poll();

			if(visited[current.value] == true){
				continue;
			}

			visited[current.value] = true;
			distance[current.value] = current.depth - 1;

			ArrayList<Integer> nextIntegers = getAdjacentPrimeNumbers(current.value);
			for(int next : nextIntegers){
				if(visited[next] == false){
					State nextState = new State(next, current.depth + 1);
					bfsQueue.add(nextState);
				}
			}
		}

		if(visited[dest] == false){
			System.out.println("Impossible");
		}else{
			int answer = distance[dest];
			System.out.println(answer);
		}
	}


	public static void fillSieve(){
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for(int p = 2; p <= MAX_NUMBER; p += 1){
			if(isPrime[p] == false){
				continue;
			}

			for(int mul = p * p ; mul <= MAX_NUMBER; mul += p){
				isPrime[mul] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		fillSieve();
		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}
class State {
	public final int value;
	public final int depth;

	public State(int value, int depth) {
		this.value = value;
		this.depth = depth;
	}
}