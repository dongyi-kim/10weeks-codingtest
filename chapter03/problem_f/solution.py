from typing import List, Tuple


def get_maximum_range_sum(cards: List[int], ranges: List[Tuple[int, int]]) -> int:
	"""
	카드들의 리스트와 구간들의 리스트가 주어질 때, 구간들의 구간합 중 최댓값을 반환합니다.
	:param cards: list of cards
	:param ranges: list of ranges
	:return: maximum range sum
	"""
	max_sum = None
	max_index = None

	n = len(cards)
	m = len(ranges)
	rangesum_cache = []  # 구간합 캐시 배열로 활용해보세요

	rangesum_cache.append(0)

	for c in cards:
		rangesum_cache.append(c + rangesum_cache[-1])

	for i in range(m):
		start, end = ranges[i]
		sum = rangesum_cache[end] - rangesum_cache[start - 1]
		if max_sum is None or max_sum < sum:
			max_sum = sum
			max_index = i + 1

	return max_index, max_sum


if __name__ == '__main__':
	n, m = [int(x) for x in input().split()]
	cards = [int(x) for x in input().split()]
	ranges = [tuple(int(x) for x in input().split()) for _ in range(m)]
	max_index, max_sum = get_maximum_range_sum(cards, ranges)
	print(f"{max_index} {max_sum}")
