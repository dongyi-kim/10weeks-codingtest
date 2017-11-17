#include<cstdio>

bool is_prime(int n)
{ 
	//1이하는 소수가 아니다 
	if( n <= 1 )
		return false;
	
	//2는 무조건 소수다 
	if( n == 2 )
		return true;
	
	//다른 짝수는 모두 소수가 아니다 
	if( n % 2 == 0)
		return false;
	
	
	// n의 제곱근과 같거나 작은 모든 홀수에 대하여 
	for(long long i = 3; i*i<= n; i+=2)
	{
		//하나라도 약수가 존재한다면 소수가 아니다
		if( n % i == 0){
			return false;	
		}	
	}	
	
	//약수가 하나도 존재하지 않는다면 소수라고 반환한다
	return true;
}
void test_case()
{
	int n;
	scanf("%d", &n);
	if(is_prime(n))
	{
		printf("YES\n");
	}else
	{
		printf("NO\n");
	}
}

int main() {
	int tc;
	scanf("%d", &tc);
	for(int i = 1 ; i <= tc; i++)
	{
		printf("Case #%d\n", i);
		test_case();
	}
  return 0;
}
