import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		String s1 = scanner.next();
		String s2 = scanner.next();

		MyString mys1 = new MyString(s1);
		MyString mys2 = new MyString(s2);

		int result = mys1.compareTo(mys2);

		if( result < 0 )
		{
			System.out.println(-1);
		}else if (result > 0)
		{
			System.out.println(1);
		}else{
			System.out.println(0);
		}
	}

}

class MyString implements Comparable<MyString>
{
	private char[] characters;

	public MyString(String original)
	{
		characters = original.toCharArray();
	}
	
	public MyString(char[] original)
	{
		characters = new char[original.length];
		for(int i = 0; i < characters.length ; i++)
		{
			characters[i] = original[i];
		}
	}

	/**
	 * this와 파라미터 o를 비교하여
	 *   - this가 사전순으로 앞서면 음수
	 *   - o가 사전순으로 앞서면 양수
	 *   - 동일만 문자열이면 0
	 *  을 반환하는 함수를 설계하시오
	 * @param other
	 * @return
	 */
	@Override
	public int compareTo(MyString other) {
		int n = Math.min(this.characters.length, other.characters.length);
		for(int i = 0 ; i < n ; i++)
		{
			if(this.characters[i] < other.characters[i])
			{
				return -1;
			}else if(this.characters[i] > other.characters[i])
			{
				return 1;
			}
		}
		return this.characters.length - other.characters.length;
	}

	/**
	 * 두 문자열이 일치하면 true, 아니면 false를 반환하는 함수를 작성하시오.
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
        // 다른 클래스에 속하는 객체는 비교하지 말자 
		if(!(obj instanceof MyString))
			return false;

        // 길이가 다른 문자열은 애초에 같은 문자열일 수 없다
		MyString other = (MyString)obj;
		if( other.characters.length != this.characters.length )
			return false;

		int n = this.characters.length;
		for(int i = 0 ; i < n; i++)
		{
			if( this.characters[i] != other.characters[i])
			{
                // 한 글자라도 다르다면 서로 다른 문자열이다 
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return new String(characters);
	}
}
