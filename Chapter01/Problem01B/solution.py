def get_max(data, n):
	"""
	n개의 정수 data 배열에서 가장 큰 값을 반환하는 함수

	:param data:
	:param n:
	:return:
	"""
	answer = data[0]

	for i in range(n):
		if answer < data[i]:
			answer = data[i]

	return answer


if __name__ == "__main__":
	n = int(input())

	data = [int(word) for word in input().split()]

	answer = get_max(data, n)

	print(answer)
