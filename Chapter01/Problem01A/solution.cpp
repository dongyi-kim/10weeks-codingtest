#include <cstdio>
// <algorithm> 헤더를 사용하지 말고 직접 구현해보세요
int get_max(int a, int b)
{
    if( a >= b){
        return a;
    }else{
        return b;
    }
}

int main() {
    int p, q;
    scanf("%d %d", &p, &q);
    int answer = get_max(p, q);
    printf("%d\n", answer);
    return 0;
}
