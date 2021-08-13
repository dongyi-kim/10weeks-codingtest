def get_count(data, n, m, s):
	"""

	:param data:	각 사람들의 키를 저장한 배열
	:param n:		사람들의 수
	:param m:	미주의 키
	:param s:	지수의 키
	:return:	미주 혹은 지수와  키가 일치하는 사람의 수
	"""
	count = 0

	for value in data:
		if value == m or value == s:
			count += 1

	return count


if __name__ == "__main__":
	n, m, s = [int(word) for word in input().split()]

	heights = [int(word) for word in input().split()]

	answer = get_count(heights, n, m, s)

	print(answer)
