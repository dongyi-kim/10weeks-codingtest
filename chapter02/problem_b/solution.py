# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

from typing import List


def is_ordered(data: List[int]) -> bool:
	"""
	주어진 배열이 오름차순으로 정렬되어있는지 검사하는 함수
	:param data: 	정수 배열
	:return: 		정렬된 상태라면 True, 아니라면 False
	"""
	n = len(data)
	for i in range(1, n):
		if data[i - 1] > data[i]:
			return False

	return True


if __name__ == "__main__":
	n = int(input())
	data = [int(w) for w in input().split()]
	print(f'{"YES" if is_ordered(data) else "NO"}')
