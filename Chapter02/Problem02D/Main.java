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
     * @param o
     * @return
     */
    @Override
    public int compareTo(MyString o) {
        int n = Math.min(this.characters.length, o.characters.length);
        for(int i = 0 ; i < n ; i++)
        {
            if(this.characters[i] < o.characters[i])
            {
                return -1;
            }else if(this.characters[i] > o.characters[i])
            {
                return 1;
            }
        }
        return this.characters.length - o.characters.length;
    }

    /**
     * 두 문자열이 일치하면 true, 아니면 false를 반환하는 함수를 작성하시오.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MyString))
            return false;

        MyString o = (MyString)obj;
        if( o.characters.length != this.characters.length )
            return false;

        int n = this.characters.length;
        for(int i = 0 ; i < n; i++)
        {
            if( this.characters[i] != o.characters[i])
            {
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
