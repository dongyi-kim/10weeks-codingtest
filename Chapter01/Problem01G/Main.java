import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int first_ajou = -1;
        int last_ajou = -1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String name = scanner.next();
            if (name.equals("AJOU")) {
                if (first_ajou == -1) {
                    first_ajou = i;
                } else {
                    last_ajou = i;
                }

            }
        }


        System.out.println(first_ajou + " " + last_ajou);
    }
}
