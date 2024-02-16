import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();

		Item[] items = new Item[n];

		for (int i = 0; i < n; i += 1) {
			int weight = scanner.nextInt();
			int price = scanner.nextInt();
			items[i] = new Item(price, weight);
		}

		Knapsack knapsack = new Knapsack(n, capacity, items);
		int answer = 0;
		for (int totalCost = 0; totalCost <= capacity; totalCost += 1) {
			answer = Math.max(answer, knapsack.f(n - 1, totalCost));
		}

		System.out.println(answer);
	}
}

class Item {
	public final int value;
	public final int cost;

	public Item(int value, int cost) {
		this.value = value;
		this.cost = cost;
	}
}

class Knapsack {
	private static final int EMPTY = -1;
	private static final int INFINITY = 1_000_000_000;

	private Item[] items;
	private int[][] memo;
	private int n;
	private int capacity;

	public Knapsack(int n, int capacity, Item[] items) {
		this.n = n;
		this.capacity = capacity;
		this.items = items.clone();
		this.memo = new int[n][capacity + 1];
		for (int i = 0; i < n; i += 1) {
			Arrays.fill(memo[i], EMPTY);
		}
	}

	/**
	 * items[0..lastItemIndex]의 보석만을 고려하여 정확히 totalCost의 무게를 채웠을 때 얻을 수 있는 최대 가치
	 *
	 * @param lastItemIndex 마지막 보석의 인덱스
	 * @param totalCost     사용할 모든 보석의 무게 합
	 * @return
	 */
	public int f(int lastItemIndex, int totalCost) {
		if (lastItemIndex < 0 || totalCost < 0 || totalCost > capacity) {
			// 불가능한 경우
			return -INFINITY;
		} else if (memo[lastItemIndex][totalCost] != EMPTY) {
			// 이미 계산한 경우
			return memo[lastItemIndex][totalCost];
		} else if (totalCost == 0) {
			// 더 이상 아무 보석도 담을 수 없는 경우
			return 0;
		}

		// 이번 아이템을 사용하지 않는 경우
		int answer = f(lastItemIndex - 1, totalCost);

		if (items[lastItemIndex].cost <= totalCost) {
			// 이번 아이템을 사용하는 경우
			int previousCost = totalCost - items[lastItemIndex].cost;
			int previousValue = f(lastItemIndex - 1, previousCost);
			int currentValue = previousValue + items[lastItemIndex].value;

			answer = Math.max(answer, currentValue);
		}

		memo[lastItemIndex][totalCost] = answer;
		return answer;
	}
}
