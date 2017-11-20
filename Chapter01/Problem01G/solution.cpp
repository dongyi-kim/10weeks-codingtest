#include <cstdio>
#include <cstring>

int main() {

	int n;
	scanf("%d", &n);
	int first_ajou = -1;
	int last_ajou = -1;

	for (int i = 1; i <= n; i++)
	{
		char name[11];
		scanf("%s", name);

		if (strcmp(name, "AJOU") == 0)
		{
			if (first_ajou == -1)
			{
				first_ajou = i;
			}
			last_ajou = i;
		}

	}
	printf("%d %d\n", first_ajou, last_ajou);
	return 0;
}
