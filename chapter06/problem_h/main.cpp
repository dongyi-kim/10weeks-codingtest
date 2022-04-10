#include <cstdio>
#include <vector>

using namespace std;

/**
 * @brief   arr[left] ~ arr[right] 범위에서 원소 하나를 피벗으로 선택하는 함수
 *
 * @param arr
 * @param left
 * @param right
 * @return          피봇으로 선정된 원소의 인덱스를 반환한다
 */
int getPivotIndex(vector<int>& arr, int left, int right) {
	int a = left;
	int b = right;
	int c = (left + right) / 2;

	if (arr[a] <= arr[b] && arr[b] <= arr[c]) {
		return b;
	} else if (arr[a] <= arr[c] && arr[c] <= arr[b]) {
		return c;
	}
	return a;
}

/**
 * @brief     배열의 두 원소 arr[indexA] 와 arr[indexB]를 swap해주는 함수
 *
 * @param arr
 * @param indexA
 * @param indexB
 */
void swap(vector<int>& arr, int indexA, int indexB) {
	int temp = arr[indexA];
	arr[indexA] = arr[indexB];
	arr[indexB] = temp;
}

/**
 * @brief   arr[left] ~ arr[[right] 범위를 피봇 기준으로 좌우를 나눈다
 *          피봇의 왼쪽은 피봇 보다 작은 원소만, 피봇의 오른쪽은 피봇보다 큰 원소만 남긴다
 *
 * @param arr
 * @param left
 * @param right
 * @param pivotIndex
 * @return  피봇이 위치하는 중간 지점의 인덱스를 반환한다
 */
int divideArrayByPivot(vector<int>& arr, int left, int right, int pivotIndex) {
	if (left >= right) {
		return left;
	}

	// 피봇 값은 저장하고, 배열에서의 위치를 가장 앞(arr[left])로 옮긴다.
	int pivot = arr[pivotIndex];
	swap(arr, left, pivotIndex);

	// 이후 arr[left+1] ~ arr[right]부분을 분할한다

	// mid := 피벗보다 작은 원소가 존재하는 범위의 가장 오른쪽 인덱스
	int mid = left;
	for (int i = left + 1; i <= right; i += 1) {
		// 뒷 부분에서 피벗보다 작은 원소가 등장한 경우
		if (arr[i] < pivot) {
			// arr[mid]와 자리를 바꾼다
			swap(arr, mid + 1, i);

			// mid는 증가시킨다 <=> 더 작은 원소의 수가 늘었으므로
			mid += 1;
		}
	}

	// arr[left]에 보관중이던 피벗을 분할점(arr[mid])로 다시 가져온다
	swap(arr, mid, left);

	// 분할점은 mid가 된다.
	return mid;
}

/**
 * @brief   arr[left] ~ arr[right] 범위를 정렬해주는 함수
 *
 * @param arr
 * @param left
 * @param right
 */
void quickSort(vector<int>& arr, int left, int right) {
	if (left >= right) {
		return;
	}

	// 전체 범위를 나눌 피벗을 찾는다
	int pivotIndex = getPivotIndex(arr, left, right);

	// 피벗 기준으로 전체 범위를 좌우로 나누고 그 분할점을 찾는다
	int mid = divideArrayByPivot(arr, left, right, pivotIndex);
	pivotIndex = mid;   // 피벗은 분할점에 존재한다

	// 피벗 기준으로 좌우를 나누어 별도로 정렬한다
	quickSort(arr, left, pivotIndex - 1);
	quickSort(arr, pivotIndex + 1, right);
}

/**
 * @brief   quickSort 함수의 축약형
 * @param arr
 */
void quickSort(vector<int>& arr) {
	quickSort(arr, 0, arr.size() - 1);
}

int main() {
	int N;
	scanf("%d", &N);

	vector<int> arr(N);
	for (int i = 0; i < N; i += 1) {
		scanf("%d", &arr[i]);
	}

	quickSort(arr);
	for (int i = 0; i < N; i += 1) {
		if (i > 0) {
			printf(" ");
		}
		printf("%d", arr[i]);
	}
}
