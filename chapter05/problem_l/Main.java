import java.io.*;
import java.lang.*;
import java.util.*;

class ArrayUtil {

    /**
     * 두 오름차순 배열 A와 B를 하나로 합쳐 재정렬하는 함수
     *
     * @param A 배열 1
     * @param N 배열 1의 길이
     * @param B 배열 2
     * @param M 배열 2의 길이
     * @return 두 배열을 합쳐 정렬한 결과를 반환한다
     */
    public static int[] mergerTwoSortedArray(int[] A, int N, int[] B, int M) {
        int[] C = new int[N + M];
        int i = 0, j = 0, k = 0;
        // 두 배열 모두 원소가 남아있다면
        while (i < N && j < M) {
            // 두 배열의 A[i]와 B[j]중 더 작은 값을 C[k]에 저장한다.
            // 이후 i 혹은 j와 k를 증가시켜준다.
            if (A[i] < B[j]) {
                C[k++] = A[i++];
            } else {
                C[k++] = B[j++];
            }
        }
        // A 배열에 원소가 남아있다면 C의 뒷 부분에 차례로 쌓는다
        while (i < N) {
            C[k++] = A[i++];
        }
        // B 배열에 원소가 남아있다면 C의 뒷 부분에 차례로 쌓는다
        while (j < M) {
            C[k++] = B[j++];
        }

        return C;
    }
}

public class Main {
    public static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws Exception {
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] A = new int[N];
        int[] B = new int[M];

        for (int i = 0; i < N; i += 1) {
            A[i] = scanner.nextInt();
        }
        for (int j = 0; j < M; j += 1) {
            B[j] = scanner.nextInt();
        }

        int[] mergedArray = ArrayUtil.mergerTwoSortedArray(A, N, B, M);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mergedArray.length; i += 1) {
            if (i > 0) {
                builder.append(' ');
            }

            builder.append(mergedArray[i]);
        }

        System.out.println(builder.toString());
    }

}
