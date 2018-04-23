#include<cstdio>
#include<string>
#include<cstring>
#include<iostream>
#include<algorithm>
using namespace std;

const int MAX_LENGTH = 100000;

class MyString{
private:
	char *characters;
	int length;

public:
	MyString(const char * str)
	{
		this->length = strnlen(str, MAX_LENGTH);
		this->characters = new char[this->length];
		for (int i = 0; i < this->length; i += 1)
		{
			this->characters[i] = str[i];
		}
	}

	MyString(const string & original)
	{
		this->length = original.length();
		for (int i = 0; i < this->length; i += 1)
		{
			this->characters[i] = original[i];
		}
	}

	~MyString()
	{
		delete[] characters;
	}

	/**
	 * @param o      비교 할 문자열 (오른쪽 항) 
	 * @return true  this가 o보다 사전순으로 앞선다면 true
	 * @return false else
	 */
	bool operator < (const MyString & o) const{
		int n = min(this->length, o.length);
		for (int i = 0; i < n; i++)
		{
			if (this->characters[i] < o.characters[i])
			{
				return true;
			}
			else if (this->characters[i] > o.characters[i])
			{
				return false;
			}
		}
		if (this->length < o.length)
		{
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * @param o      비교 할 문자열 (오른쪽 항 ) 
	 * @return true  o가 this보다 사전순으로 앞선다면 true
	 * @return false else
	 */
	bool operator > (const MyString & o) const{
		int n = min(this->length, o.length);
		for (int i = 0; i < n; i++)
		{
			if (this->characters[i] < o.characters[i])
			{
				return false;
			}
			else if (this->characters[i] > o.characters[i])
			{
				return true;
			}
		}
		if (this->length > o.length)
		{
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * @param o      비교 할 문자열 (오른쪽 항 ) 
	 * @return true  두 문자열이 같다면 
	 * @return false 두 문자열이 다르다면
	 */
	bool operator == (const MyString& o) const{
		if (this->length != o.length)
			return false;

		for (int i = 0; i < this->length; i++)
		{
			if (this->characters[i] != o.characters[i])
			{
				return false;
			}
		}
		return true;
	}

};


int main()
{
	string s1;
	string s2;
	cin >> s1 >> s2;

	MyString mys1(s1);
	MyString mys2(s2);

	if (mys1 < mys2)
	{
		printf("-1");
	}
	else if (mys1 > mys2)
	{
		printf("1");
	}
	else{
		printf("0");
	}

	return 0;
}