import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static final int[] SEGMENTS = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
	public static final int MAX_SEGMENTS = 500_000;

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();

		Item[] items = new Item[10];
		for(int i = 0 ; i <= 9; i += 1){
			items[i] = new Item(1, SEGMENTS[i]);
		}

		Knapsack knapsack = new Knapsack(10, MAX_SEGMENTS, items);

		for(int caseIndex = 1; caseIndex <= caseNum ; caseIndex += 1){
			int totalSegments = scanner.nextInt();

			if(totalSegments==0){
				// 0개로는 아무 숫자도 만들 수 없다
				System.out.println(0);
			}else{
				// totalSegments개로 만들 수 있는 모두 숫자 문자열의 갯수
				int answer = knapsack.f(totalSegments);

				if(totalSegments > items[0].cost){
					// 그 중 맨 앞 글자가 '0'인 문자열은 제외한다
					answer -= knapsack.f(totalSegments - items[0].cost);
				}
				System.out.println(answer);
			}
		}
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

	private Item[] items;
	private int[] memo;
	private int n;
	private int capacity;

	public Knapsack(int n, int capacity, Item[] items) {
		this.n = n;
		this.capacity = capacity;
		this.items = items.clone();
		this.memo = new int[capacity + 1];
		Arrays.fill(memo, EMPTY);

	}

	/**
	 * 전체 totalCost개의 세그먼트를 사용하여 만들 수 있는 "숫자 문자열"의 수
	 * * 올바른 10진수가 아니어도 됨.
	 *
	 * @param totalCost		사용한 전체 세그먼트의 수
	 * @return				만들 수 있는 숫자 문자열의 경우의 수
	 */
	public int f(int totalCost) {
		if (totalCost < 0 || totalCost > capacity) {
			return 0;
		} else if (memo[totalCost] != EMPTY) {
			// 이미 계산한 경우
			return memo[totalCost];
		} else if (totalCost == 0) {
			// 세그먼트를 모두 사용한 경우의 수 하나 추가
			return 1;
		}

		int answer = 0;

		for(int lastDigit = 0 ; lastDigit <= 9 ; lastDigit += 1){
			// 마지막 글자가 lastDigit인 경우의 수 모두 누적
			int segments = items[lastDigit].cost;
			int previousCost = totalCost - segments;
			int previousWays = f(previousCost);
			answer += previousWays;
		}

		memo[totalCost] = answer;
		return answer;
	}
}
