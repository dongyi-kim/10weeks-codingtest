#include <cstdio>

int get_max(int a, int b)
{
    if( a >= b)
    {
        return a;
    }else{
        return b;
    }
}

int main()
{
    int p, q;
    scanf("%d %d", &p, &q);

    int answer = get_max(p, q);

    printf("%d\n", answer);
    return 0;
}
