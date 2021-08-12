# 힌트

<details>
<summary>보기</summary>
a와 b의 대소관계에 따라서 케이스를 나누어 볼 수 있다
</details>

# 해설 

<details>
<summary>해설</summary><p>
  
1. a가 b보다 크다면 a를 반환한다
2. a가 b와 같다면 a나 b를 반환한다
3. a가 b보다 작다면 b를 반환한다
  
</p></details>

# Tip

이 문제에선 설명을 위해 직접 구현하지만, 대부분의 언어는 자체적으로 최대값 함수를 제공한다.
문제풀 때 잘 활용하자

- Java - `Math.max(a,b)`
- C++ - `std::max(a, b)`
- python - `max(a,b)`
