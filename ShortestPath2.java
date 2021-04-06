public class ShortestPath2 {

    int num = 10;
    int INF = 10000000;

    int a[][] ={
        {0,12,INF,INF,15,INF,INF,INF,INF,INF},
        {12,0,4,INF,10,INF,INF,INF,INF,INF},
        {INF,4,0,INF,3,13,INF,INF,INF,INF},
        {15,INF,INF,0,INF,10,INF,INF,INF,INF},
        {INF,10,3,INF,0,INF,10,INF,INF,INF},
        {INF,INF,13,INF,INF,0,INF,INF,INF,15},
        {INF,INF,INF,7,10,INF,0,INF,19,9},
        {INF,INF,INF,21,INF,INF,INF,0,25,INF},
        {INF,INF,INF,INF,INF,INF,19,25,0,5},
        {INF,INF,INF,INF,INF,15,9,INF,5,0}
    };

    bool v[6];
    int d[6];

    int getSmallIndex() {
        int min = INF;
        int index = 0;
        for (int i = 0; i < num; i++) {
            if (d[i] < min && !v[i]) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    public class dijkstra() {
        public dijkstra(int start);
        for (int i = 0; i < num; i++) {
            d[i] = a[start][i];
        }
        v[start] = true;
        for (int i = 0; i < num - 2; i++) {
            int current = getSmallIndex();
            v[current] = true;
            for (int j = 0; j < 6; j++) {
                if (!v[j]) {
                    if (d[current] + a[current][j] < d[j]) {
                        d[j] = d[current] + a[current][j];
                    }
                }
            }
        }
    }

    public static void main(String [] args) {
        dijkstra dijk = new dijkstra();

        for (int i = 0; i < num; i++) {
            printf("%d ", d[i]);
        }
    }




}
