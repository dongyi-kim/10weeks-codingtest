#include <iostream>
#include <vector>
#include <queue>

using namespace std;


class Player{
public:
	int index;

	Player(int index){
		this->index = index;
	}
};

/**
 * 조세퍼스 게임을 수행하여 각 플레이어가 제거된 순서를 리스트로 반환하는 함수
 *
 * @param n         플레이어의 수
 * @param m         매 턴마다 건너 뛸 사람의 수
 * @param players   좌석에 앉아있는 순서대로 주어지는 플레이어 정보
 * @return
 */
vector<Player> getDeadPlayersList(int n, int m, const vector<Player>& players){
	// 현재 게임에서 제외된 플레이어들의 리스트
	vector<Player> deadPlayers;

	// 아직 게임에서 제외되지 않는 플레이어들의 리스트
	queue<Player> playerQueue;

	for(int i = 0 ; i < n; i+= 1) {
		playerQueue.push(players[i]);
	}

	for(int i = 0; i < n; i++) {
		// (m-1)명의 사람을 건너뛴다.
		int jump = 1 + (m - 1) % playerQueue.size();
		for(int j = 0; j < jump - 1; j += 1) {
			Player p = playerQueue.front();
			playerQueue.pop();
			playerQueue.push(p);
		}

		// m번째 사람은 게임에서 제외한다.
		Player dead = playerQueue.front();
		playerQueue.pop();

		// 제외 리스트에 추가한다.
		deadPlayers.push_back(dead);
	}


	return deadPlayers;
}

void process(int caseIndex) {
	int n, m;
	cin >> n >> m;

	vector<Player> players;
	for(int i = 0; i < n; i++) {
		players.push_back(Player(i + 1));
	}

	vector<Player> deadPlayers = getDeadPlayersList(n, m, players);

	for(int i = 0; i < n; i ++) {
		if(i > 0) {
			cout << " ";
		}

		Player p = deadPlayers[i];
		cout << p.index;
	}

	cout << endl;
}

int main() {
	int caseSize;
	cin >> caseSize;

	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		process(caseIndex);
	}
}

