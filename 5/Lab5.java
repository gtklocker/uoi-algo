import java.util.*;

public class Lab5 {
    public static void dfs(
        boolean[][] exists_edge,
        boolean[] visited,
        int[] team,
        int teamid,
        LinkedList<Integer> visit_order,
        int src
    ) {
        visited[src] = true;
        team[src] = teamid;
        for (int i = 1; i < exists_edge.length; ++i) {
            if (!exists_edge[src][i] || visited[i]) {
                continue;
            }

            dfs(exists_edge, visited, team, teamid, visit_order, i);
        }
        visit_order.addFirst(src);
    }
    public static void dfs_team(
        boolean[][] exists_edge,
        boolean[] visited,
        int[] team,
        int[] outDegree,
        int[] inDegree,
        int src
    ) {
        visited[src] = true;
        for (int i = 1; i < exists_edge.length; ++i) {
            if (exists_edge[src][i] && team[i] != team[src]) {
                ++outDegree[team[src]];
                ++inDegree[team[i]];
            }

            if (!exists_edge[src][i] || visited[i]) {
                continue;
            }

            dfs_team(exists_edge, visited, team, outDegree, inDegree, i);
        }
    }
    public static void whichTeam(int[] team, int src) {
        System.out.println(team[src]);
    }
    public static void whoInTeam(int[] team, int teamid) {
        for (int i = 1; i < team.length; ++i) {
            if (team[i] == teamid) {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        boolean[][] edge = new boolean[n + 1][n + 1];
        boolean[][] edge_t = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                edge[i][j] = false;
                edge_t[i][j] = false;
            }
        }

        int from, to;
        for (int i = 1; i <= m; ++i) {
            from = in.nextInt();
            to = in.nextInt();
            edge[from][to] = true;
            edge_t[to][from] = true;
        }

        boolean[] visited = new boolean[n + 1];
        boolean[] visited_t = new boolean[n + 1];
        int[] team = new int[n + 1];
        int[] team_t = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            visited[i] = false;
            visited_t[i] = false;

            team[i] = 0;
            team_t[i] = 0;
        }

        LinkedList<Integer> visit_order = new LinkedList<Integer>();
        int teamid = 0, teamid_t = 0;
        for (int i = 1; i <= n; ++i) {
            if (team[i] == 0) {
                dfs(edge, visited, team, ++teamid, visit_order, i);
                i = 0;
            }
        }

        ListIterator<Integer> it = visit_order.listIterator();
        int node;
        while (it.hasNext()) {
            node = it.next();
            if (!visited_t[node]) {
                dfs(edge_t, visited_t, team_t, ++teamid_t, new LinkedList<Integer>(), node);
            }
        }

        System.out.print("node\t");
        for (int i = 1; i <= n; ++i) {
            System.out.print(i);
            if (i < n) {
                System.out.print("\t");
            }
        }
        System.out.print("\n");

        System.out.print("team\t");
        for (int i = 1; i <= n; ++i) {
            System.out.print(team_t[i]);
            if (i < n) {
                System.out.print("\t");
            }
        }
        System.out.print("\n");

        // classify H/L teams
        int[] inTeam = new int[teamid_t + 1];
        int[] outTeam = new int[teamid_t + 1];
        for (int i = 1; i <= teamid_t; ++i) {
            inTeam[i] = 0;
            outTeam[i] = 0;
        }

        for (int i = 1; i <= n; ++i) {
            visited[i] = false;
        }
        for (int i = 1; i <= n; ++i) {
            if (!visited[i]) {
                dfs_team(edge, visited, team_t, outTeam, inTeam, i);
            }
        }


        for (int i = 1; i <= teamid_t; ++i) {
            if (outTeam[i] == 0) {
                System.out.println("Team " + i + " is Low.");
            }
            if (inTeam[i] == 0) {
                System.out.println("Team " + i + " is High.");
            }
        }
    }
}
