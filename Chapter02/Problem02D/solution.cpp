#include<cstdio>

int strcmp(const char* sa, const char* sb)
{
	int i; 
	for (i = 0; sa[i] == sb[i]; i++)
	{
		if (sa[i] == '\0' || sb[i] == '\0')
		{
			break;
		}
	}

	if (sa[i] < sb[i])
	{
		return -1;
	}
	else if (sa[i] > sb[i])
	{
		return 1;
	}
	return 0;
}

int main() {
	char sa[100000 + 1];
	char sb[100000 + 1];
	scanf("%s", sa);
	scanf("%s", sb);
	int result = strcmp(sa, sb);
	if (result < 0)
	{
		printf("-1\n");
	}
	else if (result > 0)
	{
		printf("1\n");
	}
	else {
		printf("0\n");
	}

	return 0;
}
