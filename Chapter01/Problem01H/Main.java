import java.util.Scanner;

class Main {

    static int H;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();

        int[] inputs = new int[100000];
        for (int i = 0; i < H; i++) {
            inputs[i] = sc.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < H; i++) {
            sum += inputs[i];
        }

        int min = 0;
        for (int i = 0; i < H; i++) {
            int mind = mabs(sum - inputs[min] * H);
            int di = mabs(sum - inputs[i] * H);
            if (di < mind) {
                min = i;
            }
        }
        System.out.printf("%d %d", min + 1, inputs[min]);
    }

    private static int mabs(int input) {
        if (input >= 0) return input;
        return -input;
    }
}
