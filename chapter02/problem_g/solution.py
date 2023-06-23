from typing import List


def bubble_sort(data: List[int]):
	"""
	선택 정렬을 수행한다.
	:param data: 정수 배열
	"""
	n = len(data)

	for i in range(n - 1):  # Pair로 검사하므로 마지막 칸 제외 
		for j in range(n - i - 1):  # 반복 횟수만큼 마지막 원소들은 이미 정렬되어 있다 
			if data[j] > data[j + 1]:
				data[j], data[j + 1] = data[j + 1], data[j]


if __name__ == "__main__":
	n = int(input())
	data = [int(w) for w in input().split()]
	bubble_sort(data)
	print(' '.join([str(w) for w in data]))
