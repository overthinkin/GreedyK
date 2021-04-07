class Graph {
    int n;              //노드의 수
    int[][] weight;     //노드 사이의 가중치를 저장할 변수
    int[] D;            //시작 노드에서 현재 노드까지의 최단거리를 저장할 변수
    int[] visted;       //방문한 노드들을 체크할 변수

    Graph(int n) {
        weight = new int[n][n];
        for(int i=0; i<n;i++)           //노드 사이의 가중치를 0으로 초기화
            for(int j=0; j<n;j++)       //노드 사이의 가중치가 0이면 노드가 연결 되어있지 않음
                weight[i][j] = 0;
        D = new int[n];
        visted = new int[n];
    }
    public void input(int i, int j, int w) {            //노드 사이의 가중치 입력
        weight[i][j] = w;
        weight[j][i] = w;
    }
    public void first(int s){                                   //시작노드 s
        for(int i = 0; i<n; i++) D[i] = Integer.MAX_VALUE;      //배열 D[] = Integer.MAX_VALUE 으로 초기화,단 D[s] = 0
        D[s] = 0;
        for(int i=0; i<n; i++) visted[i] = 0;                   //visted == 0 이면 아직 방문 안한 노드, 1이면 이미 방문한 노드
        visted[s] = 1;                                          //visted[s] = 1 로 초기화
    }
}

public class ShortestPath {

    public void Dijkstra(int[][] weight,int[] D,int[] visted) {       //다익스트라 알고리즘
        int Dmin = Integer.MAX_VALUE;
        int min_index = -1;
        for(int i = 0; i<D.length; i++){
            if(visted[i] == 1){
                for(int j=0; j<D.length; j++){
                    if(Dmin > D[i]+weight[i][j] && visted[j] == 0 && weight[i][j] != 0) {
                        Dmin = D[i] + weight[i][j];
                        min_index = j;
                    }
                }
            }
        }
        D[min_index] = Dmin;
        visted[min_index] = 1;
    }
    // for문을 이용해 시작 노드와 연결되어있는 방문하지 않은 노드중 Dmin값이 최소인 노드를 구한다.
    // Dmin의 값이 D[i] + weight[i][j]의 값보다 큰 경우 Dmin값을 D[i] + weight[i][j]값으로 갱신한다.
    // for 문이 끝나면 D[min_index]를 Dmin값으로 갱신한다. visted[min_index]를 1로 갱신한다.
    // 노드의수 - 1 만큼 반복한다.
    public static void main(String[] args) {
        long start = System.currentTimeMillis();     //코드 start 시간
        Graph graph = new Graph(10);
        graph.input(0,1,12);                //ppt 그대로 노드 사이의 가중치 input
        graph.input(0,2,15);
        graph.input(1,4,4);
        graph.input(1,5,10);
        graph.input(2,3,21);
        graph.input(2,6,7);
        graph.input(3,7,25);
        graph.input(4,5,3);
        graph.input(4,8,13);
        graph.input(5,6,10);
        graph.input(6,7,19);
        graph.input(6,9,9);
        graph.input(7,9,5);
        graph.input(8,9,15);
        graph.first(0);                           //시작노드 입력
        ShortestPath shortestPath = new ShortestPath();
        for(int i=0; i < 10-1; i++)                 //모든 노드들을 방문할때까지 반복. (노드의 수 - 1)번
            shortestPath.Dijkstra(graph.weight,  graph.D, graph.visted);
        for(int i = 0; i<10; i++){                  //결과값 출력
            System.out.printf("%d번 노드에서 %d번 노드까지 가는 최단거리: %d", 0, i ,graph.D[i]);
            System.out.println();
        }
        long end = System.currentTimeMillis();      //코드 end 시간

        System.out.println("수행시간 : "+ (end-start)+ "ms");       //코드의 (end-start)시간을 계산하여 수행시간을 구한다.
    }
}
