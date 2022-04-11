#include <cstdio>
#include <vector>

using namespace std;

/**
 * @brief           독립적으로 정렬 된 두 범위 arr[left]~arr[mid], arr[mid+1] ~ arr[right] 범위를 하나의 정렬된 배열로 병합하는 함수
 *
 * @param arr
 * @param left
 * @param mid
 * @param right
 * @param buffer    병합을 위해 사용 될 임시 공간
 */
void concatOrderedArrays(vector<int>& arr, int left, int mid, int right, vector<int>& buffer) {
	int i = left;       // 왼쪽 배열의 인덱스 반복자
	int j = mid + 1;    // 오른쪽 배열의 인덱스 반복자
	int k = left;       // 버퍼 영역의 인덱스 반복자

	// 두 범위 모두 하나 이상의 원소가 남은 경우
	while (i <= mid && j <= right) {
		// 두 영역의 남은 원소들 중 더 작은 원소를 차례로 버퍼에 추가한다
		if (arr[i] <= arr[j]) {
			buffer[k++] = arr[i++];
		} else {
			buffer[k++] = arr[j++];
		}
	}

	// 왼쪽 영역만 원소가 남은 경우
	while (i <= mid) {
		buffer[k++] = arr[i++];
	}

	// 오른쪽 영역만 원소가 남은 경우
	while (j <= right) {
		buffer[k++] = arr[j++];
	}

	// 버퍼에 병합 된 배열을 다시 원본 배열에 복사한다
	for (int index = left; index <= right; index += 1) {
		arr[index] = buffer[index];
	}
}

/**
 * @brief           arr[left] ~ arr[right] 범위를 오름 차순으로 정렬해주는 함수
 *
 * @param arr
 * @param left
 * @param right
 * @param buffer    병합을 위해 사용 될 임시 공간
 */
void mergeSort(vector<int>& arr, int left, int right, vector<int>& buffer) {
	if (left >= right) {
		// 범위에 데이터가 하나 이하라면 정렬이 불필요하다
		return;
	}

	// 영역을 반으로 나눌 중간 인덱스를 계산한다
	int mid = (left + right) / 2;

	// 좌/우 절반을 별도로 정렬한다
	mergeSort(arr, left, mid, buffer);
	mergeSort(arr, mid + 1, right, buffer);

	// 독립적으로 정렬된 두 배열을 병합한다
	concatOrderedArrays(arr, left, mid, right, buffer);
}

/**
 * @brief       mergeSort   함수 축약형
 *
 * @param arr
 */
void mergeSort(vector<int>& arr) {
	int N = arr.size();
	vector<int> buffer(N);
	mergeSort(arr, 0, N - 1, buffer);
}

int main() {
	int N;
	scanf("%d", &N);

	vector<int> arr(N);
	for (int i = 0; i < N; i += 1) {
		scanf("%d", &arr[i]);
	}

	mergeSort(arr);
	for (int i = 0; i < N; i += 1) {
		if (i > 0) {
			printf(" ");
		}
		printf("%d", arr[i]);
	}
}
