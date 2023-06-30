from typing import List, Tuple


def get_most_least_colors(n: int, paints: List[Tuple[int, int, int]]) -> Tuple[int, int]:
	"""
	가장 많이 등장한 뒷자리를 반환하는 함수
	:param n:		좌석의 개수
	prints:		좌석의 색상을 변경하는 명령어들의 리스트
	:return:		(가장 많이 사용된 색상, 가장 적게 사용된 색상)
	"""
	m = len(paints)

	min_color = -1
	max_color = -1

	seats = [0] * n

	for left, right, color in paints:
		for i in range(left, right + 1):
			seats[i - 1] = color

	min_color = max_color = seats[0]

	freq = [0] * 100
	for color in seats:
		freq[color] += 1

	for color in range(0, 100):
		if freq[color] == 0: continue
		if freq[color] > freq[max_color]:
			max_color = color
		if freq[color] < freq[min_color]:
			min_color = color

	return max_color, min_color


if __name__ == '__main__':
	n, m = [int(i) for i in input().split()]
	paints = [[int(i) for i in input().split()] for j in range(m)]
	max_color, min_color = get_most_least_colors(n, paints)
	print(max_color)
	print(min_color)
