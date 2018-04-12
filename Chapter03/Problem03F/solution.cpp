#include<iostream>

using namespace std;
int main()
{
	int cards[100001];
	int left[100001];
	int right[100001];
	long long sum[100001];
	int n, m;
	
	cin >> n >> m ;
	sum[0] = 0;
	for(int i = 1; i <= n ; i++)
	{
		cin >> cards[i];
		sum[i] = sum[i-1] + cards[i];
	}
	
	int maxi = 1;
	for(int i = 1; i<= m ; i++)
	{
		cin >> left[i] >> right[i];
		
		long long before = sum[right[maxi]] - sum[left[maxi]-1];
		long long now = sum[right[i]] - sum[left[i]-1];
		if(now > before)
		{
			maxi = i;
		}
		
	}
	
	cout << maxi << " " << sum[right[maxi]] - sum[left[maxi]-1];
	
	
	return 0;
}