# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

from typing import List


def get_element_type_count(data: List[int]) -> int:
	"""
	중복을 제외한 숫자의 종류의 수를 계산하는 함수
	:param data: 	정수 배열
	:return: 		원소의 종류의 수 
	"""
	n = len(data)
	count = 1
	for i in range(1, n):
		if data[i - 1] != data[i]:
			count += 1
	return count


if __name__ == "__main__":
	n = int(input())
	data = [int(w) for w in input().split()]
	answer = get_element_type_count(data)
	print(answer)
