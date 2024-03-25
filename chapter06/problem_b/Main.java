import java.util.*;
import java.lang.*;

public class Main {
	public static final Scanner scanner = new Scanner(System.in);



	/**
	 * @brief   시작 기둥(from)으로부터 목적지 기둥(to)까지 num개의 원반을 모두 옮기는 최소 이동 횟수를 계산하는 함수
	 *
	 *
	 * @param num       현재 시작 기둥(from)에 놓여져 있는 원반의 수
	 * @param from      num개의 원반들이 꽂혀있는 기둥
	 * @param buffer    비어있는 기둥
	 * @param to        원반들을 모두 옮길 기둥
	 * @return          모든 원반을 옮길 수 있는 최소 이동 횟수
	 */
	public static int getMinimumMove(int num, Bar from, Bar buffer, Bar to) {
		if (num == 0) {
			// 원판이 없다면 당연히 아무 이동도 필요하지 않다
			return 0;
		}else if(num == 1){
			// 원판이 하나 뿐이라면 그냥 이동하면 된다
			Disk d = from.stack.pop();
			to.stack.push(d);
			// System.out.println(from.name + " -> " + to.name);
			return 1;
		}

		int move = 0;

		// 현재 가장 아래의 원판을 제외하고 모두 buffer 기둥으로 옮긴다
		move += getMinimumMove(num - 1, from, to, buffer);

		// 가장 아래의 원판을 목적지 기둥으로 옮긴다
		move += getMinimumMove(1, from, buffer, to);

		// 아까 옮겨두었던 N-1개의 원판들을 이제 차례로 목적지 기둥에 쌓는다
		move += getMinimumMove(num - 1, buffer, from, to);

		return move;
	}



	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();

		Bar A = new Bar("A");
		Bar B = new Bar("B");
		Bar C = new Bar("C");

		// 첫 번째 기둥에 N개의 원판을 쌓는다
		for (int i = N; i >= 1; i -= 1) {
			Disk d = new Disk(i);
			A.stack.push(d);
		}

		int answer = getMinimumMove(N, A, B, C);

		System.out.println(answer);
	}
}

class Bar{
	String name;
	Stack<Disk> stack;
	Bar(String name){
		this.name = name;
		this.stack = new Stack<>();
	}
}

class Disk {
	final int size;

	public Disk(int size) {
		this.size = size;
	}
}
