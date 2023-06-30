from typing import List


def get_most_frequent_number(numbers: List[str]) -> str:
	"""
	가장 많이 등장한 뒷자리를 반환하는 함수
	:param numbers: 전화번호 목록
	:return:		가장 많이 등장한 뒷자리
	"""
	n = len(numbers)
	cnt = [0] * 10000  # cnt[XXXX] := 뒷 자리가 XXXX인 전화번호의 수
	for i in range(n):
		num = int(numbers[i])
		cnt[num] += 1

	max_index = 0  # 가장 많이 등장한 뒷자리 후보
	for num in range(10000):  # 모든 뒷자리에 대하여
		if cnt[num] > cnt[max_index]:
			max_index = num

	return f"{max_index:04d}"


if __name__ == '__main__':
	n = int(input())
	numbers = [input().strip() for i in range(n)]
	print(get_most_frequent_number(numbers))
