import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * 코드를 제출하기 전에 꼭!!! 아래 두 가지를 지켜주세요
 * - 클래스 이름을 'Main.java'으로 변경해주세요
 * - 제일 윗줄의 패키지 임포트 명령어를 삭제해주세요
 **/
public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * 두 정수 a와 b중 더 큰 값을 반환하는 함수
     *
     * @param a
     * @param b
     * @return a와 b중 더 큰 값
     */
    public static int getMax(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) throws Exception {
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        int answer = getMax(p, q);

        System.out.println(answer);
    }

}
