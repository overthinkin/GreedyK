# Team: GreedyK

member: 이종웅, 최태성, 장진이

date: 2021/04/07

#### Mission: 다익스트라 알고리즘을 이용하여 주어진 그래프의 최단 경로 찾기

---

#### 코드의 구조

   1. Graph
         1. 데이터 입력과 초기화 메소드 구현
         2. 다익스트라 알고리즘을 메소드로 구현
               1. Dijkstra 메소드
               2. 연결 노드 D 갱신
               3. 최소값 찾기
               4. 최단거리 배열 D 계산
               5. 출력
2. Main
3. 결과

---

## 1. Graph

### 1. 데이터 입력과 초기화 메소드 구현

`Graph` 클래스에서는 노드의 수와 가중치를 저장할 배열을 초기화하고 가중치를 입력할 수 있는 메소드를 생성한다.

```java
class Graph {
    int n;              //노드의 수
    int[][] weight;     //노드 사이의 가중치를 저장할 변수

    Graph(int n) { //노드의 수를 입력
        this.n = n; //슈퍼클래스에 저장
        weight = new int[n+1][n+1]; //가중치 배열은 1을 추가하여 자기자신의 가중치도 저장할 수 있도록 한다.
        for (int i = 0; i < n; i++)   //노드 사이의 가중치를 0으로 초기화
            for (int j = 0; j < n; j++)
                weight[i][j] = 0;
    }

    public void input(int i, int j, int w) { //노드 사이 가중치를 입력할 메소드
        weight[i][j] = w; 
        weight[j][i] = w; //j노드에서 i노드로 가는 거리도 같으므로 저장해야한다
    }

```



### 2. 다익스트라 알고리즘을 메소드로 구현

#### Dijkstra 메소드 

`Graph` 클래스 안에 다익스트라 알고리즘을 메소드로 구현했다.

조건에 맞게 최단거리를 저장할 배열 `D`와, v로부터 최단거리가 확정되지 않은 점을 찾을 배열 `visited` 를 생성하고

입력한 int v는 시작할 노드이므로, `D[v]`와 `visited[v]`의 값을 지정한다.

```java
 public void Dijkstra(int v) { //v는 시작할 노드 
        int D[] = new int[n+1];  // 최단거리 저장 변수
        boolean[] visted = new boolean[n + 1]; // 방문노드 변수
        for (int i = 0; i < n+1 ; i++) {
            D[i] = Integer.MAX_VALUE;      //D의 값을 무한으로 초기화
        }

        D[v] = 0;      // 시작노드값 초기화
        visted[v] = true;

```

#### 연결 노드 D 갱신

본격적으로, 최단거리 배열 D에 거리를 저장하기 위해 

조건 `if (!visted[i] && weight[v][i] != 0)`으로 거리가 0이 아니면서 방문하지 않은 노드 i를 배열 `D`에 입력하는 반복문을 작성한다.

```java
for (int i = 0; i < n + 1; i++) {          //연결노드 D 갱신
            if (!visted[i] && weight[v][i] != 0) {
                D[i] = weight[v][i];
            }
        }
```

---

#### 최소값 찾기 

거리의 최소값을 찾기 위해 변수 min이 무한에서 시작해서 최소값을 만나면 갱신하는 반복문을 작성한다. 방문하지 않은 노드 중에서 min의 무한대 값이 아닌 거리값을 가지고 있다면, min과 D[i]의 값을 비교하여 D[i]의 거리값이 작으면 min에 대입하고 min의 인덱스도 i로 변경한다.

`visted[min_index] = true;` 구문으로 최소값을 가진 노드에 방문했다는 것을 기록한다.

```java
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
```

#### 최단거리 배열 D 계산

마지막으로 배열 D에 이미 들어있는 거리가 최소값을 찾는 반복문에서 나온 거리보다 크다면 min_index의 값과 그 가중치를 더하여 D[i]의 진정한 최소값이 나오도록 한다.

```java
 for (int i = 0; i < n + 1; i++) {
                if (!visted[i] && weight[min_index][i] != 0) {
                    if (D[i] > D[min_index] + weight[min_index][i]) {
                        D[i] = D[min_index] + weight[min_index][i];
                    }
                }
            }
        }
```

---

이 구문으로 배열 D가 출력된다.

```java
 for (int i = 0; i < n + 1; i++) {
            System.out.print(D[i] + " ");
        }
        System.out.println("");
    }
}
```



## 2. Main

```java
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(9);   //노드의 수를 설정하고
        graph.input(0, 1, 12);         //ppt 그대로 노드 사이의 가중치 input
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
        graph.Dijkstra(0); //다익스트라를 0(서울)에서 시작하는 것으로 실행한다
    }
}
```



|  **서울 = 0**   |   **천안 = 1**   |   **원주 = 2**   |   **강릉 = 3**  |   **논산 = 4**   |
| :----------: | :----------: | :----------: | :----------: | :----------: |
| **대전 = 5** | **대구 = 6** | **포항 = 7** | **광주 = 8** | **부산 = 9** |



## 3. 결과

![결과]("https://user-images.githubusercontent.com/80513292/113898858-34070380-9807-11eb-8469-b4b9293cbd54.png")



















