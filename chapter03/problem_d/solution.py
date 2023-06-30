MOD = 100_000_000

fib_cache = None  # 전역 캐시 배열로 활용해보자


def fib(n: int) -> int:
	"""
	피보나치 수열을 구하는 함수
	:param n:
	:return: 피보나치 수열의 n번째 항을 MOD로 나눈 나머지
	"""
	global fib_cache
	global MOD

	if fib_cache is None:
		fib_cache = [0, 1]
		for i in range(2, 1_000_001):
			fib_cache.append((fib_cache[i - 1] + fib_cache[i - 2]) % MOD)

	return fib_cache[n]


if __name__ == '__main__':
	case_num = int(input())
	for _ in range(case_num):
		n = int(input())
		fib_n = fib(n)
		print(fib_n)
