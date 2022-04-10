def get_frequent_number(n, height, month, m):
    arr = []
    for i in range(n):
        if month[i] == m:
            arr.append([height[i], month[i]])

    if arr is []:
        return -1
    else:
        return max(arr)[0]


if __name__ == "__main__":
    n = int(input())
    height = list(map(int, input().split()))
    month = list(map(int, input().split()))
    m = int(input())

answer = get_frequent_number(n, height, month, m)

print(answer)
