#include <cstdio>

//get_max(a, b) := a와 b중 더 큰 값을 반환하는 함수
int get_max(int a, int b)
{
    if( a >= b)
    {
        //만약 a가 b이상의 값을 가진다면?
        //a를 반환하면 이 함수의 의미에 부합한다
        return a;
    }else{
        //만약 b가 a이상의 값을 가진다면?
        //b를 반환하면 이 함수의 의미에 부합한다
        return b;
    }
}

int main()
{
    //두 정수 p와 q를 입력받는다
    int p, q;
    scanf("%d %d", &p, &q);

    //p와 q중 더 큰값을 반환하는 함수를 이용해 문제의 정답을 구한다
    int answer = get_max(p, q);

    //정답을 형식에 맞게 출력한다
    printf("%d\n", answer);
    return 0;
}
