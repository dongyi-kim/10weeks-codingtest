#include<cstdio>

int getRowIndex(int index)
{
	return index / 9;
}

int getColIndex(int index)
{
	return index % 9;
}

int getGroupIndex(int index)
{
	int r = getRowIndex(index);
	int c = getColIndex(index);
	int g = (r / 3) * 3 + (c / 3);
	return g;
}

void testCase(int caseIndex)
{
	int index;
	scanf("%d", &index);

	int r = 1 + getRowIndex(index - 1);
	int c = 1 + getColIndex(index - 1);
	int g = 1 + getGroupIndex(index - 1);

	printf("Case #%d:\n", caseIndex);
	printf("%d %d %d\n", r, c, g);
}

int main()
{
	int caseNum;
	scanf("%d", &caseNum);
	for (int caseIndex = 0; caseIndex < caseNum; caseIndex++)
	{
		testCase(caseIndex);
	}
		return 0;
}