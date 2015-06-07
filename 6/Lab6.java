import java.util.*;

class Edge implements Comparable<Edge> {
  public int from;
  public int to;
  public int weight;
  public Edge(int from, int to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }
  public String toString() {
    return "(" + this.from + ", " + this.to + ", " + this.weight + ")";
  }
  public int compareTo(Edge other) {
    return Integer.compare(this.weight, other.weight);
  }
}

class CountingQueue {
  private List<Edge> edges = new ArrayList<Edge>();
  private List<List<Edge>> bucket;
  private int n;
  private int first;
  public CountingQueue(int n) {
    this.n = n;
    this.bucket = new ArrayList<List<Edge>>(n + 1);
    for (int i = 0; i <= this.n; ++i) {
      this.bucket.add(new ArrayList<Edge>());
    }
  }
  public void add(Edge e) {
    this.bucket.get(e.weight).add(e);
  }
  public void setup() {
    List<Edge> current;
    for (int i = 0; i <= this.n; ++i) {
      current = this.bucket.get(i);
      if (current.isEmpty()) {
        continue;
      }

      for (Edge e : current) {
        edges.add(e);
      }
    }

    this.first = 0;
  }
  public int size() {
    try {
      Edge lightest = edges.get(this.first);
      return edges.size() - this.first;
    } catch (IndexOutOfBoundsException ex) {
      return 0;
    }
  }
  public Edge poll() {
    try {
      return edges.get(this.first++);
    } catch (IndexOutOfBoundsException ex) {
      return null;
    }
  }
}

class UnionFind {
  private int n;
  private int[] Pi;
  public UnionFind(int n) {
    this.n = n;
    this.Pi = new int[n + 1];
    for (int i = 1; i <= n; ++i) {
      this.Pi[i] = i;
    }
  }
  public void union(int a, int b) {
    if (this.find(a) == this.find(b)) {
      return;
    }

    int teamb = this.find(b);
    for (int k = 1; k <= n; ++k) {
      if (this.Pi[k] == teamb) {
        this.Pi[k] = this.Pi[a];
      }
    }
  }
  public int find(int a) {
    return this.Pi[a];
  }
}

public class Lab6 {
  private static final int INF = 0xbadcafe;
  private static final int UNDEFINED = 0xbadbabe;
  private static final int MAX_WEIGHT = 50;
  private static int n, m;
  private static int x, y;
  private static int[][] edge;
  private static int[] dist, prev;
  private static HashSet<Edge> mst;

  private static void dijkstra(int src) {
    PriorityQueue<Edge> Q = new PriorityQueue<Edge>();

    for (int i = 1; i <= n; ++i) {
      dist[i] = INF;
      prev[i] = UNDEFINED;
    }
    dist[src] = 0;

    int u;
    Q.add(new Edge(UNDEFINED, src, 0));
    while (Q.size() > 0) {
      u = Q.poll().to;
      for (int i = 1; i <= n; ++i) {
        if (edge[u][i] == INF) {
          continue;
        }
        if (dist[u] + edge[u][i] < dist[i]) {
          dist[i] = dist[u] + edge[u][i];
          prev[i] = u;
          Q.add(new Edge(UNDEFINED, i, dist[i]));
        }
      }
    }
  }

  public static void kruskal() {
    CountingQueue Q = new CountingQueue(MAX_WEIGHT);
    UnionFind UF = new UnionFind(n);
    mst = new HashSet<Edge>();

    int u = y;
    while (u != x) {
      mst.add(new Edge(prev[u], u, edge[prev[u]][u]));
      UF.union(prev[u], u);
      u = prev[u];
    }

    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (edge[i][j] == INF) {
          continue;
        }
        Q.add(new Edge(i, j, edge[i][j]));
      }
    }

    Q.setup();
    Edge e;
    while (Q.size() > 0) { // |V| * O(|V|) = O(|V|^2)
      e = Q.poll(); // O(1)
      if (UF.find(e.from) != UF.find(e.to)) {
        mst.add(e);
        UF.union(e.from, e.to); // O(|V|)
      }
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    m = in.nextInt();

    edge = new int[n + 1][n + 1];
    dist = new int[n + 1];
    prev = new int[n + 1];
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
      edge[to][from] = weight;
    }

    x = in.nextInt();
    y = in.nextInt();

    dijkstra(x);
    kruskal();
    System.out.println(mst);
  }
}
