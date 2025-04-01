import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OptimalBuildingPlacement {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDist(2, 2, 1));
    }

    public static class BuildingPlacement {
        int H, W, min;
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        public int findMinDist(int h, int w, int n) {
            this.H = h;
            this.W = w;
            this.min = Integer.MAX_VALUE;
            int[][] grid = new int[h][w];
            for (int[] gr : grid) {
                Arrays.fill(gr, -1);
            }
            backtrack(grid, 0, n);
            return min;
        }

        private void bfs(int[][] grid) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (grid[i][j] == 0) {
                        q.add(new int[] { i, j });
                    }
                }
            }
            int dist = 0;

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    for (int[] dir : dirs) {
                        int r = dir[0] + curr[0];
                        int c = dir[1] + curr[1];
                        if (r >= 0 && c >= 0 && r < H && c < W && !visited[r][c]) {
                            visited[r][c] = true;
                            q.add(new int[] { r, c });
                        }
                    }
                }
                dist++;
            }
            dist--;
            min = Math.min(min, dist);
        }

        private void backtrack(int[][] grid, int idx, int cnt) {
            if (cnt == 0) {
                bfs(grid);
            }

            for (int i = idx; i < H * W; i++) {
                int r = i / W;
                int c = i % W;

                grid[r][c] = 0;
                backtrack(grid, i + 1, cnt - 1);
                grid[r][c] = -1;
            }

        }
    }
}
