#include <stdio.h>


//data[i] := i번째 사람의 생일을 정수로 저장한 것 
int data[200000];

//birthCount[b] := 생일이 십진수로 b인 사람이 현재 검사 영역에 몇 명 존재하는가 카운트 
int birthCount[1000000];

int main()
{
	int n, k;	// n : 사람의 수, k : 선택할 좌석의 수 
	int answer = 0;	// 생일이 모두 다른 연속한 k개의 좌석이 존재하는 경우의 수 
	int uniqueDates = 0;	//현재 검사하는 영역에서 오직 1명만 존재하는 유일한 생일이 몇 종류 있는가?
	
	//데이터 입력받기 
	scanf("%d %d", &n, &k);
	for(int i = 0 ; i < n ; i ++)
	{
		scanf("%d", &data[i]);
	}
	
	//각 생일별 카운트를 초기화한다
	for(int birth = 000000; birth<=991231; birth++)
	{
		birthCount[birth] = 0;
	}
	
	//하나의 사람을 잡고 그 사람을 오른쪽 끝에 앉은 사람이라고 하자 
	//[i-k+1, i]의 k개의 좌석을 검사하고 한 칸씩 범위를 오른쪽으로 옮겨가며 조사한다
	for(int i = 0 ; i < n ; i++)
	{
		int right = i;	//i가 오른쪽 끝이라고 생각하자
		int left = right -  k + 1; //왼쪽 끝 칸의 인덱스를 계산하자
		int removed = left - 1;	//바로 이전 단계에서 왼쪽 끝에 있던 사람은 범위에서 제거한다 
		int added = right;	//right번째에 있는 사람은 이번에 범위에 추가된다 
		
		//새로 추가 될 사람의 생일 
		int newBirth = data[added];
		
		//이 생일인 사람이 한 명 늘었으므로 카운트 업 
		birthCount[newBirth] += 1;
		
		//만약 이 번에 등장한 생일이 현재 카운트가 1이 됬다면 ? -> 유일하다.
		if(birthCount[newBirth] == 1)
		{
			uniqueDates += 1;	
		}else if(birthCount[newBirth] == 2)
		{	//반대로 이 사람이 등장함으로써 두 명이 되었다면? -> 유일하지 않아졌다 
			uniqueDates -= 1;	
		}
		
		//이 번에 제거해야 할 사람이 있다면 
		if( removed >= 0 )
		{	//그 사람의 생일을 카운트 다운 한다 
			int birth = data[removed];
			birthCount[birth] -= 1;
			
			//그 사람의 생일이 그 사람만 유일하게 가졌었다면? -> 유일한 생일이 줄었다 
			if(birthCount[birth] == 0)
			{
				uniqueDates -= 1;	
			}else if(birthCount[birth] == 1)
			{	//그 사람이 나감으로서 그 생일을 가진 사람이 한 명이 되었다면? -? 유일하다 
				uniqueDates += 1;
			}
		}

		//현재 k명이 범위에 속하며 k명 모두 유일한 생일을 이 범위 내에서 가지고 있다면 하나의 경우의 수다
		if(left >= 0 && uniqueDates == k)
		{
			answer += 1;
		}
	}
	
	printf("%d\n", answer);
	return 0;
}
