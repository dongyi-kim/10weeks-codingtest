from typing import List, Tuple


def get_covered_area(points: List[Tuple[int, int]]) -> int:
	"""
	100x100 도화지에서 색종이들이 차지하는 면적을 구하는 함수
	:param points: 각 색종이의 왼쪽 아래 좌표
	:return: 색종이들이 차지하는 면적
	"""
	n = len(points)
	answer = 0

	paper = [[0] * 100 for _ in range(100)]
	for x, y in points:
		for i in range(x, x + 10):
			for j in range(y, y + 10):
				paper[i][j] += 1

	for x in range(100):
		for y in range(100):
			if paper[x][y] > 0:
				answer += 1

	return answer


if __name__ == '__main__':
	case_num = int(input())
	for _ in range(case_num):
		n = int(input())
		points = [(int(x) for x in input().split()) for _ in range(n)]
		print(get_covered_area(points))
