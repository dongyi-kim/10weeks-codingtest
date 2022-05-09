def is_ordered(data, n):
	for i in range(n - 1, 0, -1):
		if data[i-1] > data[i]:
			return False
	
	return True


if __name__ == "__main__":
	n = int(input())
	data = [ int(w) for w in input().split() ]
	result = is_ordered(data, n)
	if result :
		print("YES")
	else:
		print("NO")
