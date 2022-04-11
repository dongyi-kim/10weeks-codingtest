import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static int getMaximumRangeDifference(int n, int k, City[] cities){
		int answer = 0;

		// 소득이 가장 작은 도시부터 pop되는 우선순위 큐
		PriorityQueue<City> rangeMinimum = new PriorityQueue<>();

		// 소득이 가장 높은 도시부터 pop되는 우선순위 큐
		PriorityQueue<City> rangeMaximum = new PriorityQueue<>(Collections.reverseOrder());


		for(int i = 0 ; i < n ; i+=1){
			City c = cities[i]; // 각 도시 c에 대하여 차례로

			// c를 오른쪽 끝으로 하는 k개의 연속된 도시에 대해
			int rightEnd = i;
			int leftEnd = rightEnd - k + 1;

			// c를 각 우선순위 큐에 추가한다
			rangeMaximum.add(c);
			rangeMinimum.add(c);

			// 최대 혹은 최소 소득을 가진 도시가 검사 범위 밖에 있다면
			// 불필요한 정보이므로 모두 pop하여 제거한다.
			while(rangeMaximum.isEmpty() == false
					&& rangeMaximum.peek().index < leftEnd){
				rangeMaximum.poll();
			}

			while(rangeMinimum.isEmpty() == false
					&&  rangeMinimum.peek().index < leftEnd){
				rangeMinimum.poll();
			}

			if(leftEnd < 0){
				continue;
			}

			// 검사 범위 내에 존재하는 최대/최소 소득간의 차이를 계산한다
			int minIncome = rangeMinimum.peek().income;
			int maxIncome = rangeMaximum.peek().income;
			int diff = maxIncome - minIncome;

			// 최대의 차이를 갱신한다
			answer = Math.max(answer, diff);
		}

		return answer;
	}

	public static void testCase(int caseIndex) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		City[] cities = new City[n];

		for(int i = 0 ; i < n ; i += 1){
			int income = scanner.nextInt();
			cities[i] = new City(i, income);
		}

		int answer = getMaximumRangeDifference(n, k, cities);

		System.out.println(answer);
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}


class City implements  Comparable<City>{
	public final int index;     // 도시의 인덱스
	public final int income;    // 해당 도시의 소득

	public City(int index, int income){
		this.index = index;
		this.income = income;
	}

	@Override
	public int compareTo(City o) {
		// 소득에 대해 우선순위를 가지도록 대소관계를 정의해준다
		return this.income - o.income;
	}
}