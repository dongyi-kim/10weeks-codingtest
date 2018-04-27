import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 조세퍼스 게임을 수행하여 각 플레이어가 제거된 순서를 리스트로 반환하는 함수
	 *
	 * @param n         플레이어의 수
	 * @param m         매 턴마다 건너 뛸 사람의 수
	 * @param players   좌석에 앉아있는 순서대로 주어지는 플레이어 정보
	 * @return
	 */
	public static ArrayList<Player> getDeadPlayersList(int n, int m, Player[] players){
		// 현재 게임에서 제외된 플레이어들의 리스트
		ArrayList<Player> deadPlayers = new ArrayList<>();

		// 아직 게임에서 제외되지 않는 플레이어들의 리스트
		Queue<Player> playerQueue = new LinkedList<>();
		for(int i = 0 ; i < n; i+= 1){
			playerQueue.add(players[i]);
		}

		for(int i = 0 ; i < n ; i++){
			// (m-1)명의 사람을 건너뛴다.
			int jump = m % playerQueue.size();
			for(int j = 0; j<jump - 1; j+=1){
				Player p = playerQueue.poll();
				playerQueue.add(p);
			}

			// m번째 사람은 게임에서 제외한다.
			Player dead = playerQueue.poll();

			// 제외 리스트에 추가한다.
			deadPlayers.add(dead);
		}

		return deadPlayers;
	}

	public static void testCase(int caseIndex) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		Player[] players = new Player[n];
		for(int i = 0 ; i < n ; i++)
		{
			players[i] = new Player(i+1);
		}

		ArrayList<Player> deadPlayers = getDeadPlayersList(n,m, players);

		for(int i = 0 ; i < n ; i ++){
			if( i > 0 ){
				System.out.print(" ");
			}

			Player p = deadPlayers.get(i);
			System.out.print(p.index);
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class Player{
	public final int index;

	public Player(int index){
		this.index = index;
	}
}