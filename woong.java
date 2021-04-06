class Graph {
    int n;              //노드의 수
    int[][] weight;     //노드 사이의 가중치를 저장할 변수

    Graph(int n) {
        this.n = n;
        weight = new int[n+1][n+1];
        for (int i = 0; i < n; i++)           //노드 사이의 가중치를 0으로 초기화
            for (int j = 0; j < n; j++)
                weight[i][j] = 0;
    }

    public void input(int i, int j, int w) {
        weight[i][j] = w;
        weight[j][i] = w;
    }

    public void Dijkstra(int v) {
        int D[] = new int[n+1];  // 최단거리 저장 변수
        boolean[] visted = new boolean[n + 1]; // 방문노드 변수
        for (int i = 0; i < n+1 ; i++) {
            D[i] = Integer.MAX_VALUE;      //D의 값 초기하
        }

        D[v] = 0;      // 시작노드값 초기화
        visted[v] = true;

        for (int i = 0; i < n + 1; i++) {          //연결노드 D 갱신
            if (!visted[i] && weight[v][i] != 0) {
                D[i] = weight[v][i];
            }
        }

        for (int a = 0; a < n - 1; a++) {
            int min = Integer.MAX_VALUE;
            int min_index = -1;

            //최소값 찾기
            for (int i = 0; i < n + 1; i++) {
                if (!visted[i] && D[i] != Integer.MAX_VALUE) {
                    if (D[i] < min) {
                        min = D[i];
                        min_index = i;
                    }
                }
            }

            visted[min_index] = true;
            for (int i = 0; i < n + 1; i++) {
                if (!visted[i] && weight[min_index][i] != 0) {
                    if (D[i] > D[min_index] + weight[min_index][i]) {
                        D[i] = D[min_index] + weight[min_index][i];
                    }
                }
            }
        }
        for (int i = 0; i < n + 1; i++) {
            System.out.print(D[i] + " ");
        }
        System.out.println("");
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.input(0, 1, 12);                //ppt 그대로 노드 사이의 가중치 input
        graph.input(0, 2, 15);
        graph.input(1, 4, 4);
        graph.input(1, 5, 10);
        graph.input(2, 3, 21);
        graph.input(2, 6, 7);
        graph.input(3, 7, 25);
        graph.input(4, 5, 3);
        graph.input(4, 8, 13);
        graph.input(5, 6, 10);
        graph.input(6, 7, 19);
        graph.input(6, 9, 9);
        graph.input(7, 9, 5);
        graph.input(8, 9, 15);
        graph.Dijkstra(0);
    }
}