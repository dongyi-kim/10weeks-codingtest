def get_max(a, b):
	"""
	두 정수 a와 b중 더 큰 값을 반환하는 함수

	:param a:
	:param b:
	:return:
	"""
	if a > b:
		return a
	else:
		return b


if __name__ == "__main__":
	p, q = [int(word) for word in input().split()]

	answer = get_max(p, q)

	print(answer)
