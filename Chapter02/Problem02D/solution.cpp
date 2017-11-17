#include<cstdio>

int strcmp(const char* sa, const char* sb)
{	
	int i; //가장 처음으로 두 문자열이 달라지거나 종료되는 인덱스 
	for(i = 0 ; sa[i] == sb[i]; i++)
	{
		//둘 중 하나의 문자열이 종료되면 i를 그만 증가시키고 끝낸다 
		if(sa[i] == '\0' || sb[i] == '\0')
		{
			break;	
		}
	}
	
	//마지막 비교 대상 문자의 아스키 코드 대소관계로 비교한다
	if(sa[i] < sb[i])
	{
		return -1;
	}else if(sa[i] > sb[i])
	{
		return 1;	
	}
	return 0;
}

int main() {
	char sa[100000+1];
	char sb[100000+1];
	scanf("%s", sa);
	scanf("%s", sb);
	int result = strcmp(sa, sb);
	if( result < 0 )
	{
		printf("-1\n");
	}else if(result > 0)
	{
		printf("1\n");
	}else{
		printf("0\n");
	}
	
  return 0;
}
