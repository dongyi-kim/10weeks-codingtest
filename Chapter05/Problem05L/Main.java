import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		int N = scanner.nextInt();
		Timestamp[] logs = new Timestamp[N + 2];

		logs[0] = new Timestamp(-1, "00:00");
		logs[N + 1] = new Timestamp(-1, "48:00");
		for (int i = 1; i <= N; i += 1) {
			int teamIndex = scanner.nextInt();
			String stringTimestamp = scanner.next();
			logs[i] = new Timestamp(teamIndex, stringTimestamp);
		}

		int pointA = 0;
		int pointB = 0;
		long timeA = 0;
		long timeB = 0;

		for (int i = 1; i <= N+1; i += 1) {
			if (pointA > pointB) {
				timeA += logs[i - 1].getElapsedTimeTo(logs[i]);
			} else if (pointA < pointB) {
				timeB += logs[i - 1].getElapsedTimeTo(logs[i]);
			}

			if (logs[i].teamIndex == 1) {
				pointA += 1;
			} else if (logs[i].teamIndex == 2) {
				pointB += 1;
			}
		}

		System.out.printf("%02d:%02d\n", timeA / 60, timeA % 60);
		System.out.printf("%02d:%02d\n", timeB / 60, timeB % 60);
	}

}

class Timestamp {
	public final long timeInSeconds;
	public final int teamIndex;

	public Timestamp(int teamIndex, String stringTimestamp) {
		this.teamIndex = teamIndex;

		String[] splited = stringTimestamp.split(":");
		int minutes = Integer.parseInt(splited[0]);
		int seconds = Integer.parseInt(splited[1]);
		this.timeInSeconds = minutes * 60 + seconds;
	}

	public Timestamp(int teamIndex, int minutes, int seconds) {
		this.teamIndex = teamIndex;
		this.timeInSeconds = minutes * 60 + seconds;
	}

	public long getElapsedTimeTo(Timestamp next) {
		long dt = Math.abs(next.timeInSeconds - this.timeInSeconds);
		return dt;
	}
}