import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		String input = scanner.next();
		int n = input.length();
		FenceRepairing problem = new FenceRepairing(input);
		double answer = problem.f(n - 1);
		System.out.printf("%.3f", answer);
	}
}

class FenceRepairing {
	private static final double EMPTY = -1;
	private double[] memo;            // DP 상태공간
	private boolean[] isBroken;        // 각 팬스가 고장났는지의 여부
	private int n;                    // 팬스의 길이

	public FenceRepairing(String input) {
		n = input.length();
		this.memo = new double[n];
		Arrays.fill(memo, EMPTY);
		this.isBroken = new boolean[n];
		for (int i = 0; i < n; i += 1) {
			isBroken[i] = (input.charAt(i) == 'X');
		}
	}

	/**
	 * 0번째 팬스부터 (lastIndex)번째 팬스까지 모두 완벽히 수리하는데 소요되는 최소의 비용
	 *
	 * @param lastIndex 수리할 가장 마지막(오른쪽) 팬스의 인덱스
	 * @return 그 때의 최소 비용
	 */
	public double f(int lastIndex) {
		if (lastIndex < 0) {
			// 수리할 팬스가 없는 경우
			return 0;
		} else if (memo[lastIndex] != EMPTY) {
			// 이미 계산한 적 있는 경우
			return memo[lastIndex];
		} else if(lastIndex == 1){
			return 1;
		}

		// 0번째부터 모두 한 번에 교체하는 경우
		double answer = Math.sqrt(lastIndex + 1);

		if (false == isBroken[lastIndex]) {
			// lastIndex번째가 고장나지 않은 팬스인 경우?
			// 수리하지 않고 이전까지의 수리 비용을 사용하자
			answer = f(lastIndex - 1);
		} else {
			// 현재 팬스가 교체 범위의 오른쪽 끝이라고 하자
			// 즉, [previousIndex, lastIndex]범위를 모두 한 번에 교체할 때 최소 비용
			for (int previousIndex = 0; previousIndex <= lastIndex; previousIndex += 1) {
				double newCost = 0;
				newCost += Math.sqrt(lastIndex - previousIndex + 1);
				newCost += f(previousIndex - 1);

				if (newCost < answer) {
					answer = newCost;
				}
			}
		}

		memo[lastIndex] = answer;
		return memo[lastIndex];
	}

}