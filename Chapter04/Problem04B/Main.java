import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 *
	 * @param n     전체 티켓의 수
	 * @param m     요청 고객의 수
	 * @param ids   각 고객들의 회원번호
	 * @return
	 */
	public static ArrayList<Integer> getTicketNumbers(int n, int m, int[] ids)
	{
		ArrayList<Integer> tickets = new ArrayList<>();

		TicketTable table = new TicketTable(n);

		for(int i = 0 ; i < m ; i ++)
		{
			int userId = ids[i];
			int ticketIndex = table.findEmptyIndexByUserId(userId);
			tickets.add(ticketIndex);
			table.setUsed(ticketIndex, true);
		}
		return tickets;
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt(); // 전체 티켓의 수
		int m = scanner.nextInt(); // 요청 고객의 수

		int[] ids = new int[m];

		for(int i = 0 ; i < m ; i ++)
		{
			ids[i] = scanner.nextInt();
		}

		ArrayList<Integer> tickets = getTicketNumbers(n, m, ids);

		for(int index : tickets){
			System.out.println(index);
		}
	}

}


class TicketTable
{
	private final boolean[] used;
	public final int length;

	public TicketTable(int length)
	{
		this.length = length;
		this.used = new boolean[length];
	}

	/**
	 * 사용자의 회원 번호로 지급받게 될 행운권 번호를 계산하는 메소드
	 */
	public int findEmptyIndexByUserId(int userId)
	{
		int index = userId % length;
		while(isEmpty(index) == true) {
			index = (index+1)%length;
		}
		return index;
	}

	/**
	 * 해당 행운권 번호가 이미 사용 중인지 여부를 반환하는 메소드
	 */
	public boolean isEmpty(int ticketIndex)
	{
		return used[ticketIndex];
	}

	/**
	 *  티켓 사용 여부를 갱신하기 위한 메소드
	 */
	public void setUsed(int index, boolean status)
	{
		this.used[index]  = status;
	}
}