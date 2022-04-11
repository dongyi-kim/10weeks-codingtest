import random 
import math
import string 

def testcase(tidx, level, n, name_cnt):
    names = [ ''.join(random.choices(string.ascii_uppercase, k=10)) for _ in range(name_cnt)]
    cnt = dict()
    max_cnt = 0
    
    with open("input.%02d.txt" % tidx, "w") as f:
        f.write("%d\n" % n)
        for i in range(n):
            name = random.choice(names)
            if name not in cnt:
                cnt[name] = 0
            cnt[name] = cnt[name] + 1
            max_cnt = max(max_cnt, cnt[name])
            f.write("%s\n" % name )
    
    cand = []
    for k, v in cnt.items():
        if v == max_cnt :
            cand.append(k)
            
    cand.sort()
            
    with open("output.%02d.txt" % tidx, "w") as f:
        f.write("%d\n" % (max_cnt))
        f.write("%s" % (' '.join(cand)))

for tidx in range(2, 51):
    level = 1 + (tidx-1)//5 # 1 ~ 10 
    n = 10 * (level ** 4)
    name_cnt = [1, 2 , 3 , 4 , 5 , int(math.sqrt(n)), n//4, n//3, n//2, n][tidx%10]
    testcase(tidx, level, n, name_cnt)

    