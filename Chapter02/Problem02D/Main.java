import java.util.*;

class Main {

    public static int compareTo(char[] sa, char[] sb) {
        int length1 = sa.length;
        int length2 = sb.length;

        int limit = Math.min(length1, length2);

        int k = 0;
        while (k < limit) {
            char c1 = sa[k];
            char c2 = sb[k];
            if (c1 != c2) {
                System.out.println(c1 - c2);
                return c1 - c2;
            }
            k++;
        }
        return length1 - length2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] sa = scanner.next().toCharArray();
        char[] sb = scanner.next().toCharArray();
        int result = compareTo(sa, sb);
        System.out.println(result);
        if (result < 0) {
            System.out.println(-1);
        } else if (result > 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
