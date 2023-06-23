from typing import List


def is_winnable(data: List[int], k: int) -> bool:
	"""
	게임의 규칙에 따라 현무가 승리할 수 있는 경우의 수가 존재하는지 검사하는 함수
	:param data: 	정수 배열
	:param k:			한 번에 선택할 종이컵의 수
	:return: 		현무가 승리할 수 있는 경우의 수가 하나 이상 존재한다면 True, 아니면 False
	"""
	win_count = 0
	sum = 0

	# 첫 (k-1)개의 원소에 대한 합을 계산한다.
	for i in range(k - 1):
		sum += data[i]

	for i in range(n - k + 1):
		# 영역의 왼쪽 끝 인덱스 i에 대해
		if i > 0:
			# 영역을 벗어나게 되는 원소 제외
			sum -= data[i - 1]
		# 새로 영역에 들어온 원소 추가
		sum += data[i + k - 1]

		if sum % 2 == 0:
			# 승리 가능한 지 여부만 중요하므로 탈출
			win_count += 1
			break

	return win_count > 0  # 승리할 수 있는 경우의수가 하나라도 존재한다면 승리 가능하다 


if __name__ == "__main__":
	n, k = [int(w) for w in input().split()]
	data = [int(w) for w in input().split()]
	print(f"{'YES' if is_winnable(data, k) else 'NO'}")
