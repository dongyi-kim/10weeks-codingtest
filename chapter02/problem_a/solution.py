# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

from typing import List


def get_maximum_height(heights: List[int], months: List[int], m: int) -> int:
	"""
	생일이 m월인 가장 큰 키의 도토리를 찾는 함수
	:param heights: 각 도토리의 키
	:param months:	각 도토리의 달
	:param m:		찾을 생일이 속한 달
	:return:		해당 달에 속한 도토리의 가장 큰 키 
	"""
	n = len(heights)
	answer = -1  # 존재하지 않으면 -1

	for i in range(n - 1, -1, -1):  # 오름차순이므로 뒤에서부터 탐색
		if months[i] == m:
			answer = heights[i]
			break  # 찾았으면 더이상 탐색할 필요 없음

	return answer


if __name__ == "__main__":
	# 데이터의 수를 입력받는다
	n = int(input())
	heights = [int(w) for w in input().split()]
	months = [int(w) for w in input().split()]
	m = int(input())
	answer = get_maximum_height(heights, months, m)
	print(answer)
