import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * 괄호들의 정보가 차례로 배열로 주어질 때, 올바른 괄호 문자열인지 판단하는 함수
	 *
	 * @param n             괄호 문자열의 길이
	 * @param parentheses   괄호 문자에 대한 배열
	 * @return
	 */
	public static boolean isValidParentheses(int n, Parenthesis[] parentheses) {
		Stack<Parenthesis> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			// 왼쪽부터 오른쪽의 괄호를 차례로 조회한다.
			Parenthesis p = parentheses[i];

			if (p.type == Parenthesis.OPEN) {
				// 열린 괄호라면 짝을 찾을 때 까지 스택에 보관한다
				stack.push(p);
			} else if (p.type == Parenthesis.CLOSE) {
				// 닫힌 괄호 p에 대하여

				if (stack.size() > 0 && stack.peek().type == Parenthesis.OPEN) {
					// 가장 마지막에 추가된 열린 괄호와 짝을 맞출 수 있으므로 제거한다
					stack.pop();
				} else {
					// 짝을 맞출 수 있는 열린 괄호가 없다면 올바르지 않은 괄호 문자열이다.
					return false;
				}
			}
		}

		if (stack.size() > 0) {
			// 아직 스택에 짝을 찾지 못한 열린 괄호가 남아있다.
			return false;
		}

		return true;
	}

	public static void testCase(int caseIndex) {
		String input = scanner.next();
		int n = input.length();

		Parenthesis[] parentheses = new Parenthesis[n];

		for (int i = 0; i < n; i++) {
			parentheses[i] = new Parenthesis(i, input.charAt(i));
		}

		boolean isValid = isValidParentheses(n, parentheses);

		if (isValid) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	public static void main(String[] args) throws Exception {
		int caseSize = scanner.nextInt();

		for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
			testCase(caseIndex);
		}
	}

}

class Parenthesis {
	public static final boolean OPEN = true;
	public static final boolean CLOSE = false;

	public final boolean type;  // 열린 괄호면 true, 닫힌 괄호면 false
	public final int index;     // 해당 괄호의 인덱스

	public Parenthesis(int index, boolean type) {
		this.index = index;
		this.type = type;
	}

	public Parenthesis(int index, char c) {
		this.index = index;
		if (c == '(') {
			this.type = OPEN;
		} else {
			this.type = CLOSE;
		}
	}
}