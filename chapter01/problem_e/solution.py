def solve(data, n, p, q):
	"""
	동아리 멤버들의 탑승 가능 여부를 출력하는 함수

	:param data:	각 멤버들의 몸무게 data[0] ~ data[n-1]
	:param n:		멤버들의 수
	:param p:	놀이기구 탑승 가능 체중 제한
	:param q:	놀이기구 최대 하중
	"""

	count = 0
	sum = 0

	for weight in data:
		if weight <= p:
			count += 1
			sum += weight

	print("%d %d" % (count, sum))
	if sum <= q:
		print("YES")
	else:
		print("NO")


if __name__ == "__main__":
	n, p, q = [int(word) for word in input().split()]

	weights = [int(word) for word in input().split()]

	solve(weights, n, p, q)
