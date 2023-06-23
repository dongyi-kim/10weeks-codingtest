# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

import math
from typing import List, Tuple


class point2d:
	def __init__(self, x: int = 0, y: int = 0):
		self.x: int = x
		self.y: int = y

	def get_squared_dist(self, other: "point2d") -> int:
		"""
		두 점(self, other) 사이의 거리의 제곱을 반환합니다.
		:param other:	다른 점
		:return: 		두 점 사이의 거리의 제곱
		"""
		return (self.x - other.x) ** 2 + (self.y - other.y) ** 2

	def get_dist(self, other: "point2d") -> float:
		"""
		두 점(self, other) 사이의 거리를 반환합니다.
		:param other:	다른 점
		:return: 		두 점 사이의 거리
		"""
		return math.sqrt(self.get_squared_dist(other))


def closest_pair(points: List[point2d]) -> Tuple[float, int]:
	"""
	주어진 점들 중 가장 가까운 두 점 사이의 거리를 반환합니다.
	:param points:	점들의 리스트
	:return:		가장 가까운 두 점 사이의 거리
	"""
	min_dist: float = -1  # 가장 가까운 쌍의 거리
	min_count: int = -1  # 가장 가까운 쌍의 수
	n = len(points)

	for i in range(n):
		for j in range(i + 1, n):  # 같은 쌍, 중복 쌍을 무시하기 위해 i+1부터 시작
			dist = points[i].get_squared_dist(points[j])
			if min_dist == -1 or dist < min_dist:  # 처음이거나 더 작은 거리를 찾은 경우
				min_dist = dist
				min_count = 1
			elif dist == min_dist:  # 같은 거리를 가진 쌍을 찾은 경우
				min_count += 1

	return math.sqrt(min_dist), min_count


def main():
	n = int(input())
	points: List[point2d] = []
	for i in range(n):
		x, y = [int(w) for w in input().split()]
		points.append(point2d(x, y))
	min_dist, min_count = closest_pair(points)
	print(f"{min_dist:.1f}")
	print(min_count)


if __name__ == "__main__":
	main()
