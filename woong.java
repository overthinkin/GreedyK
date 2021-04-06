class Graph {
    int n;              //����� ��
    int[][] weight;     //��� ������ ����ġ�� ������ ����

    Graph(int n) {
        this.n = n;
        weight = new int[n+1][n+1];
        for (int i = 0; i < n; i++)           //��� ������ ����ġ�� 0���� �ʱ�ȭ
            for (int j = 0; j < n; j++)
                weight[i][j] = 0;
    }

    public void input(int i, int j, int w) {
        weight[i][j] = w;
        weight[j][i] = w;
    }

    public void Dijkstra(int v) {
        int D[] = new int[n+1];  // �ִܰŸ� ���� ����
        boolean[] visted = new boolean[n + 1]; // �湮��� ����
        for (int i = 0; i < n+1 ; i++) {
            D[i] = Integer.MAX_VALUE;      //D�� �� �ʱ���
        }

        D[v] = 0;      // ���۳�尪 �ʱ�ȭ
        visted[v] = true;

        for (int i = 0; i < n + 1; i++) {          //������ D ����
            if (!visted[i] && weight[v][i] != 0) {
                D[i] = weight[v][i];
            }
        }

        for (int a = 0; a < n - 1; a++) {
            int min = Integer.MAX_VALUE;
            int min_index = -1;

            //�ּҰ� ã��
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
        graph.input(0, 1, 12);                //ppt �״�� ��� ������ ����ġ input
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