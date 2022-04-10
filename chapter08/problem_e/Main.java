import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final int MAXIMUM_VIRUS = 10000;

	public static void testCase(int caseIndex) {
		int targetNumber = scanner.nextInt();

		int[] distance = new int[MAXIMUM_VIRUS + 1];
		boolean[] visited = new boolean[MAXIMUM_VIRUS + 1];

		State initialState = new State(1, 1);
		Queue<State> queue = new LinkedList<>();
		queue.add(initialState);

		while (queue.isEmpty() == false) {
			State current = queue.poll();

			if(current.numberOfVirus > MAXIMUM_VIRUS){
				continue;
			}else if (visited[current.numberOfVirus] == true) {
				continue;
			}

			visited[current.numberOfVirus] = true;
			distance[current.numberOfVirus] = current.depth - 1;

			State nextIncrease = new State(current.numberOfVirus * 2, current.depth + 1);
			State nextDecrease = new State(current.numberOfVirus / 3, current.depth + 1);

			queue.add(nextIncrease);
			queue.add(nextDecrease);
		}

		int answer = distance[targetNumber];
		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class State {
	public final int numberOfVirus;
	public final int depth;

	public State(int numberOfVirus, int depth) {
		this.numberOfVirus = numberOfVirus;
		this.depth = depth;
	}
}