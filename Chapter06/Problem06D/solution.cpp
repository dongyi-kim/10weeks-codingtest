#include<cstdio>
#include<queue>

using namespace std;




void test_case()
{
	int n;	//초기 사람의 수 
	int m;	//사을 제외할 간격 
	scanf("%d %d", &n, &m);
	
	//모든 사람을 차례로 큐에 삽입한다 
	queue<int> q;
	for(int i = 1 ; i <= n ; i ++)
	{
		q.push(i);
	}

	//총 N번에 걸쳐 규칙에 맞게 사람을 제외해나간다 
	for(int i = 0 ; i < n ; i ++)
	{   //i번째에 제외 될 사람을 선정

		//어차피 현재 남아있는 수 만큼 한 바퀴 조회하는 건 무의미하므로 나머지만큼만 조회하여 시간을 절약한다 
		int length = m % q.size(); // length < q.size();

		//제외되지 않을 사람은 큐의 앞에서 제거하여 가장 뒤로 보낸다 
		for(int j = 1 ; j < length ; j ++)
		{   
			int next = q.front();
			q.pop();
			q.push(next);
		}

		//이후 큐의 가장 앞에 남아있는 사람이 제외될 대상이다 
		int target = q.front();
		q.pop();

		//제외하고 출력한다
		printf("%d ", target);
	}
	printf("\n");
}


int main()
{	//각 테스트케이스 반복 
	int tc;
	scanf("%d", &tc);
	for(int i = 0 ; i < tc ; i++)
	{
		test_case();
	}
	return 0;
}

