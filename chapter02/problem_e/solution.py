# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean


def is_prime(n: int) -> bool:
	"""
	주어진 숫자가 소수인지 판별하는 함수
	:param n: 	판별할 정수
	:return: 	소수면 True, 아니면 False
	"""
	if n == 1: return False
	if n == 2: return True
	if n % 2 == 0: return False
	for i in range(3, int(n ** 0.5) + 1, 2):
		if n % i == 0: return False
	return True


if __name__ == "__main__":
	case_size = int(input())
	for i in range(case_size):
		n = int(input())
		print(f"Case #{i + 1}")
		print(f"{'YES' if is_prime(n) else 'NO'}")
