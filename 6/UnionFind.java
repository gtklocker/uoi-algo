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
    if (this.Pi[a] == this.Pi[b]) {
      return;
    }

    int teamb = this.Pi[b];
    for (int k = 1; k <= n; ++k) {
      if (this.Pi[k] == teamb) {
        this.Pi[k] = this.Pi[a];
      }
    }
  }
  public int find(int a) {
    return this.Pi[a];
  }
  public void printState() {
    System.out.print("[");
    for (int i = 1; i <= this.n; ++i) {
      System.out.print(this.find(i));
      if (i < n) {
        System.out.print(", ");
      }
    }
    System.out.print("]\n");
  }

  public static void main(String[] args) {
    UnionFind UF = new UnionFind(3);
    UF.printState();

    UF.union(2, 3);
    System.out.println("union(2, 3)");
    UF.printState();

    UF.union(1, 2);
    System.out.println("union(1, 2)");
    UF.printState();
  }
}
