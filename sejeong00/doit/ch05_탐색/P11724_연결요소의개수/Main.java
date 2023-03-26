package doit.ch05_탐색.P11724_연결요소의개수;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    //연결 요소 =
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;

        int nodeCnt = Integer.parseInt(st.nextToken());
        int edgeCnt = Integer.parseInt(st.nextToken());

        //문제에서 나온 노드의 인덱스가 1부터 시작하므로 편의상 노드갯수+1의 크기로 만들어준다.
        visited = new boolean[nodeCnt + 1];
        //노드의 수는 고정되어있으니 배열로, 노드당 엣지(연결되는 노드)의 개수는 노드마다 다르므로 ArrayList로 선언한다.
        //new ArrayList<Integer>[nodeCnt + 1]; -> generic array creation 오류
        //generic array creation 이란?
        //https://multifrontgarden.tistory.com/258
        graph = new ArrayList[nodeCnt + 1];

        for (int i = 0; i < nodeCnt + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        //엣지를 입력받는다.
        for (int i = 0; i < edgeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            //양방향 그래프이므로 두 노드 전부에 엣지를 추가한다.
            graph[startNode].add(endNode);
            graph[endNode].add(startNode);
        }

        //dfs(탐색할 때마다 카운트+1)
        for (int i = 1; i < nodeCnt + 1; i++) {
            if (!visited[i]) {
                Main.dfs(i);
                count++;
            }
        }
        //카운트 출력
        bw.write(String.valueOf(count));
        bw.flush();
    }

    static void dfs(int node) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        for (int i : graph[node]) {
            if (!visited[i]) {
                dfs(i);
            }
        }

    }
}
