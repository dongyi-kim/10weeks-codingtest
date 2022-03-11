#include <iostream>
#include <string>
#include <stack>
#include <vector>

using namespace std;


class Parenthesis {
public:
	bool type;  // 열린 괄호면 true, 닫힌 괄호면 false
	int index;  // 해당 괄호의 인덱스

	Parenthesis(int index, bool type) {
		this->index = index;
		this->type = type;
	}

	Parenthesis(int index, char c) {
		this->index = index;
		if (c == '(') {
			this->type = true;
		} else {
			this->type = false;
		}
	}
};

/**
 * 괄호들의 정보가 차례로 배열로 주어질 때, 올바른 괄호 문자열인지 판단하는 함수
 *
 * @param n             괄호 문자열의 길이
 * @param parentheses   괄호 문자에 대한 배열
 * @return
 */
bool isValidParentheses(const vector<Parenthesis>& parentheses) {
	// 현재 짝을 찾지 못한 열린 괄호들
	stack<Parenthesis> stack;

	for (int i = 0; i < parentheses.size(); i++) {
		// 왼쪽부터 오른쪽의 괄호를 차례로 조회한다.
		Parenthesis p = parentheses[i];

		if (p.type == true) {
			// 열린 괄호라면 짝을 찾을 때 까지 스택에 보관한다
			stack.push(p);
		} else if (p.type == false) {
			// 닫힌 괄호 p에 대하여

			if (stack.size() > 0 && stack.top().type == true) {
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

void process(int caseIndex) {
	string str;
	cin >> str;

	vector<Parenthesis> parentheses;
	for (int i = 0; i < str.length(); ++i) {
		parentheses.push_back(Parenthesis(i,str[i]));
	}

	bool isValid = isValidParentheses(parentheses);

	if (isValid) {
		cout << "YES" << endl;
	} else {
		cout << "NO" << endl;
	}
}

int main() {
	int caseSize;
	cin >> caseSize;

	for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
		process(caseIndex);
	}
}
