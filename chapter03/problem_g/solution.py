from typing import List


class frequency_table:
	"""
	생년월일의 빈도수를 저장하는 클래스
	"""
	num_uniques: int  # 현재 저장된 생년월일 중 유일한 생년월일의 수
	table: List[int]  # table[i] = 생년월일이 i인 사람의 수

	def __init__(self, table_length=1_000000):
		self.num_uniques = 0
		self.table = [0] * 1000000

	def add_birthdate(self, birthdate: int):
		count = self.table[birthdate]
		if count == 0:
			self.num_uniques += 1
		elif count == 1:
			self.num_uniques -= 1
		self.table[birthdate] += 1

	def remove_birthdate(self, birthdate: int):
		count = self.table[birthdate]
		if count == 1:
			self.num_uniques -= 1
		elif count == 2:
			self.num_uniques += 1
		self.table[birthdate] -= 1


def get_number_of_exclusive_ranges(birthdates: List[int], m: int) -> int:
	"""
	모든 원소가 서로 다른 m개의 연속된 구간의 수를 반환하는 함수
	:param birthdates: 생년월일 리스트
	:param m: 구간의 길이
	:return: 모든 원소가 서로 다른 m개의 연속된 구간의 수
	"""
	n = len(birthdates)
	table = frequency_table()
	answer = 0
	for i in range(m):
		table.add_birthdate(birthdates[i])

	if table.num_uniques == m: answer += 1

	for i in range(m, len(birthdates)):
		table.add_birthdate(birthdates[i])
		table.remove_birthdate(birthdates[i - m])
		if table.num_uniques == m: answer += 1

	return answer


if __name__ == '__main__':
	n, m = [int(x) for x in input().split()]
	birthdates = [int(x) for x in input().split()]
	answer = get_number_of_exclusive_ranges(birthdates, m)
	print(answer)
