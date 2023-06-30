from typing import List


def fill_frequency_table(data: List[int], length=100000) -> List[int]:
	table = [0] * (length + 1)
	for number in data:
		table[number] += 1
	return table


def all_unqiue_elements(numbers: List[int]) -> List[int]:
	"""
	모든 유일한 원소를 반환하는 함수
	:param numbers: 입력 값
	:return: 모든 유일한 원소를 반환
	"""
	n = len(numbers)
	table = fill_frequency_table(numbers)
	unique_elements = []
	for number in range(1, 100001):
		if table[number] == 1:
			unique_elements.append(number)
	return unique_elements

def all_unqiue_elements_by_sort(numbers: List[int]) -> List[int]:
	"""
	정렬을 활용한 풀ㅣ
	"""
	n = len(numbers)
	uniques = []
	numbers.sort()
	for i in range(n):
		if (i == 0 or numbers[i] != numbers[i-1]) and (i == n-1 or numbers[i] != numbers[i+1]):
			uniques.append(numbers[i])
	return uniques


if __name__ == "__main__":
	n = int(input())
	numbers = [int(x) for x in input().split()]
	uniques = all_unqiue_elements(numbers)
	print(' '.join([str(x) for x in uniques]))
