import  java.util.Scanner;

class Graph {
    int n;              //노드의 수
    int[][] weight;     //노드 사이의 가중치를 저장할 변수
    int[] D;            //시작 노드에서 현재 노드까지의 최단거리를 저장할 변수
    int[] visted;       //방문한 노드들을 체크할 변수

    Graph(int n) {
        weight = new int[n][n];
        for(int i=0; i<n;i++)           //노드 사이의 가중치를 0으로 초기화
            for(int j=0; j<n;j++)
                weight[i][j] = 0;
        D = new int[n];
        visted = new int[n];
    }
    public void input(int i, int j, int w) {            //노드 사이의 가중치 입력
        weight[i][j] = w;
        weight[j][i] = w;
    }
    public void first(int s){                                   //시작노드 s
        for(int i = 0; i<n; i++) D[i] = Integer.MAX_VALUE;      //배열 D[] = ∞ 으로 초기화,단 D[s] = 0
        D[s] = 0;
        for(int i=0; i<n; i++) visted[i] = 0;                   //visted == 0 이면 아직 방문 안한 노드, 1이면 이미 방문한 노드
        visted[s] = 1;                                          //visted[s] = 1 로 초기화
    }
}

public class ShortestPath {

    public void Dijkstra(int[][] weight,int[] D,int[] visted) {       //다익스트라 알고리즘
        int Dmin = Integer.MAX_VALUE;
        int a = 0;
        int b = 0;
        for(int i = 0; i<D.length; i++){
            if(visted[i] == 1){
                for(int j=0; j<D.length; j++){
                    if(Dmin > D[i]+weight[i][j] && visted[j]==0 && weight[i][j] != 0) {
                        Dmin = D[i] + weight[i][j];
                        a=i;
                        b=j;
                    }
                }
            }
        }
        D[b] = D[a] + weight[a][b];
        visted[b] = 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        System.out.printf("시작노드: ");
        int s = scanner.nextInt();                  //시작노드 입력
        graph.first(s);
        ShortestPath shortestPath = new ShortestPath();
        for(int i=0; i < 10-1; i++)                 //노드의 수 - 1 만큼 반복
            shortestPath.Dijkstra(graph.weight,  graph.D, graph.visted);
        for(int i = 0; i<10; i++){                  //결과값 출력
            System.out.printf("%d번 노드에서 %d번 노드까지 가는 최단거리: %d", s, i ,graph.D[i]);
            System.out.println();
        }
    }
}
