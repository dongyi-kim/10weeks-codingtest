def is_inside(x: int, y: int, R: int) -> bool:
	"""
	픽셀 (x, y)가 반지름이 R인 원에 포함되는지 여부를 반환하는 함수

	:param x: 	정수 x 좌표
	:param y: 	정수 y 좌표
	:param R: 	정수 반지름
	:return: 	픽셀이 원에 포함되면 True, 아니면 False
	"""
	return x ** 2 + y ** 2 < R ** 2


def get_number_of_pixels(R: int) -> int:
	"""
	반지름이 R인 원에 포함되는 픽셀의 수를 반환하는 함수

	:param R: 	정수 반지름
	:return: 	픽셀의 수
	"""

	pixels = 0
	h = R + 1  # 하강 검사를 시작할 높이
	for x in range(0, R + 1):
		for y in range(h, -1, -1):
			if is_inside(x, y, R):  # (x, y)가 최초로 원에 포함되면
				pixels += y + 1  # 히스토그램의 높이를 더한다
				h = y
				break

	return pixels * 4


if __name__ == "__main__":
	case_size = int(input())
	for case_index in range(1, case_size + 1):
		radius = int(input())
		print(f"#{case_index}")
		print(f"{get_number_of_pixels(radius)}")
