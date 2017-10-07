package P1949;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by ohjeonggyu on 2017. 9. 21..
 */
public class Solution {

    static int T;
    static int N, K;

    static int map[][];
    static boolean check[][];

    static int directions[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    public static void main(String args[]) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(bufferedReader.readLine());

        for(int t = 1 ; t <= T ; t++) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            N = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());
            int max = 0;
            map = new int[N][N];

            for(int i = 0 ; i < N ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            int ans = 0;
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(map[i][j] == max) {
                        check = new boolean[N][N];
                        check[i][j] = true;
                        ans = Math.max(ans, dfs(new Point(j, i, map[i][j], false)));
                    }
                }
            }

            System.out.println("#"+t +" " +ans);
        }

    }


    static int dfs(Point point) {
        int size = 1;
        for(int direction[] : directions) {
            int xx = point.x + direction[0];
            int yy = point.y + direction[1];

            if(xx>=0 && xx < N && yy>=0 && yy < N) {

                // 안짜름
                if(point.value > map[yy][xx] && !check[yy][xx]) {
                    check[yy][xx] = true;
                    size = Math.max(size, dfs(new Point(xx, yy, map[yy][xx], point.isCut)) + 1);
                    check[yy][xx] = false;
                }

                for(int i = 1 ; i <= K ; i++) {
                    if(!point.isCut && point.value > map[yy][xx] - i && !check[yy][xx]) {
                        check[yy][xx] = true;
                        size = Math.max(size, dfs(new Point(xx, yy, map[yy][xx] - i, true)) + 1);
                        check[yy][xx] = false;
                    }
                }


//                // 짜름
//                if(point.isCut) {
//                    if(point.value > map[yy][xx] && !check[yy][xx][0] && !check[yy][xx][1]) {
//                        check[yy][xx][1] = true;
//                        size = Math.max(size, dfs(new Point(xx, yy, map[yy][xx], point.isCut))+1);
//                        check[yy][xx][1] = false;
//                    }
//
//                    // 안짜름
//                } else {
//
//
//                    //최댓값 까지 자르기
//                    for(int i = 1 ; i <= K ; i++) {
//                        if(point.value > map[yy][xx] - i && !check[yy][xx][1]) {
//                            check[yy][xx][1] = true;
//                            size = Math.max(size, dfs(new Point(xx, yy, map[yy][xx] - i, true)) + 1);
//                            check[yy][xx][1] = false;
//                        }
//                    }
//
//                }
            }
        }

//        System.out.println(point.x + " : " + point.y + " : " +point.isCut + " : " + size);
        return size;
    }

    static class Point {
        int x;
        int y;
        int value;
        boolean isCut;

        public Point(int x, int y, int value, boolean isCut) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.isCut = isCut;
        }
    }
}
