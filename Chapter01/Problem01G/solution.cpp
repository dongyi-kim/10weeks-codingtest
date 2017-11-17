#include <cstdio>
#include <cstring>

int main() {
	
	int n;
	scanf("%d", &n);
	int first_ajou = -1;
	int last_ajou  = -1;
	
	for(int i = 1; i<= n; i++)
	{ //모든 각 학생에 대하여 1~n번째 학생을 차례로 고려한다
		
		char name[11]; //i번째 학생의 소속 학교명
		scanf("%s", name);
		
		if( strcmp(name, "AJOU") == 0)
		{	//name이 "AJOU"와 일치하는 문자열 이라면 
			// => i번재 학생의 소속 학교가 아주대학교 라면 
				
			if(first_ajou == -1)
			{	//아직 1~(i-1)번째에서 아주대 학생이 등장한 적 없다면 
				//인덱스를 저장한다 
				first_ajou = i;
				
				//이후 (i+1)~N번째에서 아주대 학생이 등장하여도 변하지 않을 것이다.
			}
			
			//새로운 학생이 발견되었다면 계속해서 덮어 쓴다
			// => 마지막 학생의 인덱스가 남게 된다
			last_ajou = i;
		}
		
	}
	printf("%d %d\n", first_ajou, last_ajou);
  return 0;
}
