import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static ArrayList<Bomb> getRemovableOrders(int n, Bomb[] bombs) {
		ArrayList<Bomb> removedList = new ArrayList<>();

		// poll시에 제거 가능한 폭탄들 중 가장 인덱스가 작은 번호가 반환될 수 있도록
		// PriorityQueue를 사용한다. 폭탄들의 우선순의는 compareTo 메소드로 정의했다.
		PriorityQueue<Bomb> removableBombs = new PriorityQueue<>();

		for (int i = 0; i < n; i += 1) {
			if (bombs[i].getChildCount() == 0) {
				removableBombs.add(bombs[i]);
			}
		}

		while (removableBombs.isEmpty() == false) {
			Bomb b = removableBombs.poll();
			b.remove();

			removedList.add(b);

			ArrayList<Bomb> parents = b.getParentBombs();
			for (int i = 0; i < parents.size(); i += 1) {
				Bomb p = parents.get(i);
				if (p.getChildCount() == 0) {
					removableBombs.add(p);
				}
			}
		}

		// 모든 폭탄이 제거되지 않았다면 null을 반환한다.
		if (removedList.size() != n) {
			return null;
		}
		return removedList;
	}

	public static void testCase(int caseIndex) throws Exception {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		Bomb[] bombs = new Bomb[n];
		for (int i = 0; i < n; i += 1) {
			bombs[i] = new Bomb(i);
		}

		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			Bomb parent = bombs[u];
			Bomb child = bombs[v];

			child.addParentBombs(parent);
		}

		ArrayList<Bomb> orders = getRemovableOrders(n, bombs);

		if (orders == null) {
			writer.write("NO\n");
		} else {
			for (int i = 0; i < n; i++) {
				Bomb b = orders.get(i);

				if (i > 0) {
					writer.write(" ");
				}
				writer.write(String.valueOf(b.index + 1));
			}
			writer.write("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}

		writer.flush();
		writer.close();
	}

}

class Bomb implements Comparable<Bomb> {
	public final int index;

	private int childCount;
	private ArrayList<Bomb> parentBombs;

	Bomb(int index) {
		this.index = index;
		this.parentBombs = new ArrayList<>();
		this.childCount = 0;
	}

	public void addParentBombs(Bomb parentBomb) {
		this.parentBombs.add(parentBomb);
		parentBomb.childCount += 1;
	}

	public ArrayList<Bomb> getParentBombs() {
		return this.parentBombs;
	}

	public int getChildCount() {
		return childCount;
	}

	public void remove() {
		for (int i = 0; i < parentBombs.size(); i += 1) {
			Bomb p = parentBombs.get(i);
			p.childCount -= 1;
		}
	}

	@Override
	public int compareTo(Bomb o) {
		// 인덱스가 작을수록 priorityQueue에서 먼저 poll 되도록
		// 우선순위를 정의한다.
		return this.index - o.index;
	}
}