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

	public static double getMaximumPassableRadius(int N, double W, Sensor[] sensors) {
		double lowerBound = 0;
		double upperBound = 100000;

		for (int i = 0; i < ITERATION_COUNT; i += 1) {
			double radius = (lowerBound + upperBound) / 2.0;

			boolean possible = isPossible(N, W, radius, sensors);

			if (possible) {
				lowerBound = radius;
			} else {
				upperBound = radius;
			}
		}

		return upperBound;
	}

	public static boolean isPossible(int N, double W, double R, Sensor[] sensors) {
		Node leftWall = new Node(N);
		Node rightWall = new Node(N + 1);
		Node[] sensorNodes = new Node[N];
		for (int i = 0; i < N; i += 1) {
			sensorNodes[i] = new Node(i);

			if (sensors[i].xPos - sensors[i].radius - 2 * R < 0) {
				leftWall.adjacentNodes.add(sensorNodes[i]);
				sensorNodes[i].adjacentNodes.add(leftWall);
			}
			if (sensors[i].xPos + sensors[i].radius + 2 * R > W) {
				rightWall.adjacentNodes.add(sensorNodes[i]);
				sensorNodes[i].adjacentNodes.add(rightWall);
			}
		}

		for (int i = 0; i < N; i += 1) {
			for (int j = i + 1; j < N; j += 1) {
				double deltaX = sensors[i].xPos - sensors[j].xPos;
				double deltaY = sensors[i].yPos - sensors[j].yPos;
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

				if (distance - sensors[i].radius - sensors[j].radius < 2 * R) {
					sensorNodes[i].adjacentNodes.add(sensorNodes[j]);
					sensorNodes[j].adjacentNodes.add(sensorNodes[i]);
				}
			}
		}

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
	public final double xPos;
	public final double yPos;
	public final double radius;

	public Sensor(double xPos, double yPos, double radius) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
	}
}

class Node {
	public final ArrayList<Node> adjacentNodes;
	public final int index;

	public Node(int index) {
		this.index = index;
		this.adjacentNodes = new ArrayList<>();
	}


}