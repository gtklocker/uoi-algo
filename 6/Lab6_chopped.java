import java.util.*;

public class Lab6 {
  private static final int INF = 9999;
  private static int n, m;
  private static int[][] edge;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    m = in.nextInt();

    edge = new int[n + 1][n + 1];
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= n; ++j) {
        edge[i][j] = INF;
      }
    }

    int from, to, weight;
    for (int i = 1; i <= m; ++i) {
      from = in.nextInt();
      to = in.nextInt();
      weight = in.nextInt();
      edge[from][to] = weight;
    }
  }
}
