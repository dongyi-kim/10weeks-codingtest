#include <cstdio>
#include <vector>

using namespace std;

template<typename T>
class ShiftingArray {
public:
	vector <T> array; // 내부에 데이터를 저장할 배열
	int length;       // 배열의 길이
	int leftIndex;

	ShiftingArray(int length) {
		this->length = length;
		this->array.resize(length);
		this->leftIndex = 0;
	}

	/**
	 * 현재 배열에서 'index'번째에 존재하는 원소를 반환하는 함수
	 *
	 * @param index
	 * @return
	 */
	T get(int index) {
		// 현재 가장 왼쪽에 존재하는 원소로부터
		// 'index'만큼 상대적으로 오른편에 존재하는 원소를 반환한다.
		int realIndex = (index + leftIndex) % length;
		return array[realIndex];
	}

	/**
	 * 현재 배열에서 'index'번째에 존재하는 원소를 'value'로 변경하는 함수
	 *
	 * @param index
	 * @param value
	 */
	void set(int index, T value) {
		// 현재 가장 왼쪽에 존재하는 원소로부터
		// 'index'만큼 상대적으로 오른편에 존재하는 원소를 수정한다.
		int realIndex = (index + leftIndex) % length;
		array[realIndex] = value;
	}

	/**
	 * 현재 배열의 모든 원소를 왼쪽으로 'times'번 이동하는 함수
	 * @param times
	 */
	void shiftLeft(int times) {
		// 배열의 원소를 왼쪽으로 시프트 한다
		// <==> 배열의 가장 왼쪽 기준점이 오른쪽으로 이동한다.
		times = times % length;
		leftIndex = (leftIndex + times) % length;
	}

	/**
	 * 현재 배열의 모든 원소를 오른쪽으로 'times'번 이동하는 함수
	 *
	 * @param times
	 */
	void shiftRight(int times) {
		// 배열의 원소를 오른쪽으로 시프트 한다
		// <==> 배열의 가장 왼쪽 기준점이 왼쪽으로 이동한다.
		times = times % length;
		leftIndex = (leftIndex - times + length) % length;
	}

	/**
	 * 현재 배열의 모든 원소를 가장 초기 위치로 되돌리는 함수
	 */
	void initializePosition() {
		// 배열이 초기 상대로 다시 시프트 된다
		// <==> 가장 왼쪽 기준점이 원래 위치로 돌아간다
		leftIndex = 0;
	}

};

int main() {
	int nNumbers;   // 배열의 원소의 수
	int nCommands;  // 처리할 명령어의 수
	scanf("%d%d", &nNumbers, &nCommands);

	// Shift 연산이 가능한 배열을 선언하고 원소를 차례로 입력한다
	ShiftingArray<int> array(nNumbers);

	for (int i = 0; i < nNumbers; ++i) {
		int val;
		scanf("%d", &val);
		array.set(i, val);
	}

	// 각 명령어를 처리한다
	for (int i = 0; i < nCommands; ++i) {
		// 명령어 정보를 입력받는다
		int cmd;
		scanf("%d", &cmd);

		if (cmd == 0) {
			// 현재 배열에 p번 인덱스에 있는 원소를 출력하는 명령
			int p;
			scanf("%d", &p);

			int value = array.get(p);
			printf("%d\n", value);
		} else if (cmd == 1) {
			// 현재 배열을 왼쪽으로 k번 shift하는 명령
			int k;
			scanf("%d", &k);

			array.shiftLeft(k);
		} else if (cmd == 2) {
			// 현재 배열을 오른쪽으로 k번 shift하는 명령
			int k;
			scanf("%d", &k);

			array.shiftRight(k);
		} else if (cmd == 3) {
			// 현재 배열을 최초의 위치로 복원하는 명령
			array.initializePosition();
		}
	}
}