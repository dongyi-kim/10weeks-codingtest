import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

	public static String[] readLine() throws Exception {
		return reader.readLine().split(" ");
	}

	public static void main(String[] args) throws Exception {
		String[] NM = readLine();
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);

		DisjointSet disjointSet = new DisjointSet(N);

		for (int i = 0; i < M; i += 1) {
			String[] command = readLine();

			int u = Integer.parseInt(command[1]);
			int v = Integer.parseInt(command[2]);

			if (command[0].equals("LINK")) {
				disjointSet.union(u, v);
				int groupSize = disjointSet.getNumberOfConnectedNodes(u);
				writer.write(String.format("SIZE = %d\n", groupSize));
			} else if (command.equals("CHECK")) {
				if (disjointSet.isConnected(u, v) == true) {
					writer.write("Connected\n");
				} else {
					writer.write("Separated\n");
				}
			}

		}

		reader.close();
		writer.flush();
		writer.close();
	}

}

class DisjointSet {
	private final int size;
	private int[] groupBoss;
	private int[] groupSize;

	public DisjointSet(int size) {
		this.size = size;
		this.groupBoss = new int[size + 1];
		this.groupSize = new int[size + 1];
		for (int nodeIndex = 1; nodeIndex <= size; nodeIndex += 1) {
			groupSize[nodeIndex] = 1;
			groupBoss[nodeIndex] = nodeIndex;
		}
	}

	public int getGroupBoss(int u) {
		if (groupBoss[u] == u) {
			return u;
		} else {
			groupBoss[u] = getGroupBoss(groupBoss[u]);
			return groupBoss[u];
		}
	}

	public void union(int u, int v) {
		if(isConnected(u, v)){
			return;
		}

		int uBoss = getGroupBoss(u);
		int vBoss = getGroupBoss(v);
		groupSize[uBoss] += groupSize[vBoss];
		groupBoss[vBoss] = uBoss;
	}

	public boolean isConnected(int u, int v) {
		int uBoss = getGroupBoss(u);
		int vBoss = getGroupBoss(v);
		return uBoss == vBoss;
	}

	public int getNumberOfConnectedNodes(int u) {
		int uBoss = getGroupBoss(u);
		return this.groupSize[uBoss];
	}
}