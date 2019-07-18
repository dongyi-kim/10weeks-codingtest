# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean

N = int(input())

candidates = {}
for i in range(N):
	name = input()
	if name not in candidates:
		candidates[name] = 0
	
	candidates[name] += 1
	
maxPoint = 0
winners = []

for name, point in candidates.items():
	if point > maxPoint:
		maxPoint = point
		winners = []
	
	if point == maxPoint:
		winners.append(name)
		
winners.sort()

print(maxPoint)
print(' '.join(winners))
	
	
	
