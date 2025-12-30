import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] early;
    static boolean[] visited;

    public static void dfs(int node) {
        visited[node] = true;

        for (int child : tree.get(node)) {
            if (visited[child]) continue;
            dfs(child);
            early[node][0] += early[child][1];
            early[node][1] += Math.min(early[child][0], early[child][1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        early = new int[N + 1][2];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; ++i) tree.add(new ArrayList<>());
        for (int i = 0; i < N - 1; ++i) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree.get(input[0]).add(input[1]);
            tree.get(input[1]).add(input[0]);
        }
        dfs(1);
        System.out.println(Math.min(early[1][0], early[1][1]));
    }
}
