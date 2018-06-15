import java.io.*;
import java.util.*;
import java.lang.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void testCase(int caseIndex) {
		double W = scanner.nextDouble();
		int N = scanner.nextInt();

		Sensor[] sensors = new Sensor[N];
		for (int i = 0; i < N; i += 1) {
			double x = scanner.nextDouble();
			double y = scanner.nextDouble();
			double r = scanner.nextDouble();
			sensors[i] = new Sensor(x, y, r);
		}

		double answer = Solution.getMaximumPassableRadius(N, W, sensors);

		System.out.printf("%.3f\n", answer);
	}

	public static void main(String[] args) {
		int caseNum = scanner.nextInt();
		for (int caseIndex = 1; caseIndex <= caseNum; caseIndex += 1) {
			testCase(caseIndex);
		}
	}
}

class Solution {
	public static final int ITERATION_COUNT = 100;

	/**
	 * 해당 복도를 통과할 수 있는 가장 큰 반지름을 계산하여 반환한다
	 *
	 * @param N       센서들의 수
	 * @param W       복도의 폭
	 * @param sensors 각 센서들의 정보
	 * @return 통과 가능한 가장 큰 반지름
	 */
	public static double getMaximumPassableRadius(int N, double W, Sensor[] sensors) {
		double lowerBound = 0;
		double upperBound = 100000;

		// 특정 횟수만큼 정밀도를 높여가며 최적의 반지름을 찾아간다
		for (int i = 0; i < ITERATION_COUNT; i += 1) {
			// 범위의 중앙값을 반지름으로 사용한다
			double radius = (lowerBound + upperBound) / 2.0;

			// 해당 반지름으로 통과가 가능한지 검사한다
			boolean possible = isPossible(N, W, radius, sensors);

			if (possible) {
				// 통과할 수 있다면 하한선을 증가시킨다
				lowerBound = radius;
			} else {
				// 통과할 수 없다면 하한선을 감소시킨다
				upperBound = radius;
			}
		}

		return upperBound;
	}

	/**
	 * 물체의 반지름이 R일 때 복도를 통과할 수 있는지 검사한다
	 *
	 * @param N       센서들의 수
	 * @param W       복도의 폭
	 * @param R       테스트할 물체의 반지름
	 * @param sensors 각 센서의 정보
	 * @return 통과할 수 있다면 True, 아니면 False를 반환한다.
	 */
	public static boolean isPossible(int N, double W, double R, Sensor[] sensors) {
		// 좌/우 벽을 각각 그래프의 노드로 선언한다
		Node leftWall = new Node(N);
		Node rightWall = new Node(N + 1);

		// 각 센서들을 노드로 정의한다.
		Node[] sensorNodes = new Node[N];

		// 각 센서와 양쪽 벽 사이의 공간을 고려하여 그래프를 모델링한다
		for (int i = 0; i < N; i += 1) {
			sensorNodes[i] = new Node(i);

			// 해당 센서와 왼쪽 벽 사이의 거리가 2R보다 작다면? 그 사이는 물체가 통과할 수 없다
			if (sensors[i].xPos - sensors[i].radius - 2 * R < 0) {
				leftWall.adjacentNodes.add(sensorNodes[i]);
				sensorNodes[i].adjacentNodes.add(leftWall);
			}

			// 해당 센서와 오른쪽 벽 사이의 거리가 2R보다 작다면? 그 사이는 물체가 통과할 수 없다
			if (sensors[i].xPos + sensors[i].radius + 2 * R > W) {
				rightWall.adjacentNodes.add(sensorNodes[i]);
				sensorNodes[i].adjacentNodes.add(rightWall);
			}
		}

		// 서로 다른 두 센서 <i, j>사이에 대해 그래프를 모델링한다
		for (int i = 0; i < N; i += 1) {
			for (int j = i + 1; j < N; j += 1) {
				double deltaX = sensors[i].xPos - sensors[j].xPos;
				double deltaY = sensors[i].yPos - sensors[j].yPos;
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				// 두 센서 영역 사이의 빈 공간이 2R보다 작다면? 두 센서 사이에는 물체가 통과할 수 없다.
				if (distance - sensors[i].radius - sensors[j].radius < 2 * R) {
					sensorNodes[i].adjacentNodes.add(sensorNodes[j]);
					sensorNodes[j].adjacentNodes.add(sensorNodes[i]);
				}
			}
		}

		// 왼쪽 벽이 오른쪽 벽과 연결성이 존재한다면?
		// 해당 복도를 통과할 수 없다는 뜻이 된다.
		// 이를 DFS로 검사한다.
		boolean[] visited = new boolean[N + 2];
		Arrays.fill(visited, false);

		Stack<Node> dfsStack = new Stack<>();
		dfsStack.add(leftWall);

		while (dfsStack.isEmpty() == false) {
			Node currentNode = dfsStack.pop();
			if (visited[currentNode.index] == true) {
				continue;
			}

			visited[currentNode.index] = true;

			for (Node nextNode : currentNode.adjacentNodes) {
				if (visited[nextNode.index] == false) {
					dfsStack.add(nextNode);
				}
			}
		}

		return !visited[rightWall.index];
	}
}

class Sensor {
	public final double xPos;    // 센서의 중심점의 x좌표
	public final double yPos;    // 센서의 중심점의 y좌표
	public final double radius; // 센서의 감지범위 반지름

	public Sensor(double xPos, double yPos, double radius) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
	}
}

class Node {
	public final ArrayList<Node> adjacentNodes;    // 인접 노드 목록
	public final int index;                        // 해당 노드의 인덱스

	public Node(int index) {
		this.index = index;
		this.adjacentNodes = new ArrayList<>();
	}


}