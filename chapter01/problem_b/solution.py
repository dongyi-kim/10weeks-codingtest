def get_sum(data, n):
	"""
	n개의 정수 data 배열에서 모든 정수의 합을 반환하는 함수

	:param data:
	:param n:
	:return:
	"""
	answer = 0

	for value in data:
		answer += value

	return answer


if __name__ == "__main__":
	n = int(input())

	data = [int(word) for word in input().split()]

	answer = get_sum(data, n)

	print(answer)
