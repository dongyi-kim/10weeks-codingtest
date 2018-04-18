#include <cstdio>
#include <vector>

using namespace std;

const int MAX_SEAT_NUMBER = 1000;
const int MAX_COLOR_NUMBER = 100;

//좌석들을 한 번 색칠하는 이벤트에 대한 정보
class Painting{
public:
  int left;
  int right;
  int color;

  Painting() { }

  Painting(int left, int right, int color) {
    this->left = left;
    this->right = right;
    this->color = color;
  }
};

/**
 *
 * @param n : 좌석의 수. 좌석은 0~(n-1)번의 번호를 가진다.
 * @param m : 좌석을 칠한 횟수.
 * @param paintings  : 좌석들을 색칠한 이벤트들의 정보
 */
void solve(int n, int m, const vector<Painting>& paintings) {
  vector<int> seats = vector<int>(n, 0); // 처음 좌석은 전부 0번으로 칠해져 있다.
  for (int i = 0; i < m; ++i) {
  	const Painting& painting = paintings[i];

  	// 각 페인팅 정보마다 좌석의 색을 업데이트 해준다.
  	for (int j = painting.left; j <= painting.right; ++j) {
  		seats[j] = painting.color;
  	}
  }

  // 각 색이 몇 번 나왔나 체크하는 배열을 생성한다.
  // 색은 0번에서 99번까지 있으므로 배열의 크기는 100으로 생성한다.
  vector<int> count(100, 0);
  for (int i = 0; i < n; ++i) {
  	++count[seats[i]];
  }

  int minColor = seats[0]; //가장 적게 등장한 색상
  int maxColor = seats[0]; //가장 많이 등장한 색상

  // 각 색상마다 최대로 나온 횟수와 최소로 나온 횟수를 비교해 저장한다.
  for (int i = 0; i < 100; ++i) {
  	if (count[i] != 0 && count[maxColor] < count[i]) {
  		maxColor = i;
  	}
  	if (count[i] != 0 && count[minColor] > count[i]) {
  		minColor = i;
  	}
  }

  printf("%d\n", maxColor);
  printf("%d\n", minColor);
}

int main() {
  int n, m;
  scanf("%d%d", &n, &m);

  // paintings[i] := i번째에 일어난 색칠 이벤트의 정보
  vector<Painting> paintings = vector<Painting>(m);

  for (int i = 0; i < m; ++i) {
    int left, right, color;
    scanf("%d%d%d", &left, &right, &color);

    // 좌석의 번호는 1번부터 시작하므로, 0 ~ (n-1)범위로 맞추기 위하여 1씩 빼준다
    paintings[i] = Painting(left - 1, right - 1, color);
  }

  // 문제의 정답을 구한다
  solve(n, m, paintings);
}