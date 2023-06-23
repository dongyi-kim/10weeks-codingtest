from typing import List


def is_consecutive(data: List[int]) -> bool:
	"""
	주어진 정수 배열이 연속적인 정수를 포함하는지 확인하는 함수
	:param data: 	정수 배열
	:return: 		연속적인 정수를 포함하면 True, 아니면 False
	"""
	maximum = data[0]
	minimum = data[0]

	for d in data:
		maximum = max(maximum, d)
		minimum = min(minimum, d)

	if maximum - minimum + 1 == len(data):
		return True
	else:
		return False


if __name__ == "__main__":
	n = int(input())
	data = [int(w) for w in input().split()]
	print(f"{'YES' if is_consecutive(data) else 'NO'}")
