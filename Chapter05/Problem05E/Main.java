import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 문제의 조건에 따라 모든 폭탄을 제거할 수 있는지 판단하는 함수
	 *
	 * @param n
	 * @param bombs
	 * @return
	 */
	public static boolean isAllRemovable(int n, Bomb[] bombs){
		// 현재 제거 완료된 폭탄들의 목록
		ArrayList<Bomb> removedList = new ArrayList<>();

		// 제거해도 전혀 이상이 없는 폭탄들의 목록
		Queue<Bomb> removableBombs = new LinkedList<>();

		// 현재 연쇄 폭탄이 없는 폭탄들은 제거 가능한 목록에 추가한다.
		for(int i = 0 ; i < n; i+=1 ){
			if(bombs[i].getChildCount() == 0){
				removableBombs.add(bombs[i]);
			}
		}

		// 제거 가능한 폭탄이 남아있는 동안 반복한다.
		while(removableBombs.isEmpty() == false){
			// 현재 연쇄폭탄이 없는 폭탄 b를 제거하고
			Bomb b = removableBombs.poll();

			// 부모 폭탄들에 대해 연쇄폭탄 정보를 갱신한다
			b.remove();

			// 그리고 제거 완료 목록에 추가한다.
			removedList.add(b);

			// 이 폭탄의 부모 폭탄들 중, 연쇄 폭탄이 모두 제거된 폭탄들은
			// 제거 가능하다. 그러므로 큐에 추가한다.
			ArrayList<Bomb> parents = b.getParentBombs();
			for(int i = 0 ; i < parents.size();  i+= 1){
				Bomb p = parents.get(i);
				if(p.getChildCount() == 0){
					removableBombs.add(p);
				}
			}
		}

		// 모든 n개의 폭탄이 제거 완료되었다면 true, else false
		if(removedList.size() == n){
			return true;
		}else{
			return false;
		}
	}

	public static void testCase(int caseIndex) {
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

		boolean removable = isAllRemovable(n, bombs);

		if(removable){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}

	}

}

class Bomb {
	public final int index;

	private int childCount; // 이 폭탄이 터졌을 때 함께 폭발하는 연쇄 폭탄의 수

	private ArrayList<Bomb> parentBombs; // 이 폭탄의 폭발을 유발할 수 있는 폭탄 리스트

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

	// 현재 이 폭탄이 제거되었을 때 폭발할 수 있는 폭탄의 수
	public int getChildCount() {
		return childCount;
	}

	public void remove() {
		// 이 폭탄을 제거한다.
		// 그러므로 부모 폭탄들은 연쇄 폭탄 하나가 사라진 꼴이 된다.
		for (int i = 0; i < parentBombs.size(); i += 1) {
			Bomb p = parentBombs.get(i);
			p.childCount -= 1;
		}
	}
}