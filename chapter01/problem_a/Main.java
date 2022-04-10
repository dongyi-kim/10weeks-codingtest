import java.io.*;
import java.lang.*;
import java.util.*;

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
