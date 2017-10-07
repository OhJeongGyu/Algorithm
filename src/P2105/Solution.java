package P2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int N;
    static int map[][];

    static int directions[][] = {{1,1}, {-1,-1}, {1,-1}, {-1,1}};
    static boolean check[][];
    /**
     * 디저트 방문 했는지.
     */
    static boolean d[];

    static int ans;

    static int memo[][];

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1 ; t <= T ; t++) {
            N = Integer.parseInt(bufferedReader.readLine());

            d = new boolean[101];
            map = new int[N][N];
            check = new boolean[N][N];
            memo = new int[N][N];
            ans = -1;

            for(int i = 0 ; i < N ; i++) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    check[i][j] = true;
                    d[map[i][j]] = true;
                    ans = Math.max(ans, go(j, i, j, i, -1, 0, 1));
                    check[i][j] = false;
                    d[map[i][j]] = false;
                }
            }
            System.out.println( "#" + t + " " +ans);
        }
    }

    static int go(int start_x, int start_y, int x, int y, int direction, int changeCount, int dessertCount) {
        int count = -1;

        if(changeCount > 4) return -1;

//        System.out.println(start_x + " : " + start_y + " : " + x + " : " + y + " : " + dessertCount + " : "+ changeCount);

        for(int i = 0 ; i < 4 ; i++) {
            int xx = x + directions[i][0];
            int yy = y + directions[i][1];

            if(start_x == xx && start_y == yy) {
                if(changeCount>=3) count = Math.max(count, dessertCount);
//                if(changeCount>=3 && changeCount <=4) return dessertCount;
            }

            if(xx>=0 && xx<N && yy>=0 && yy<N && !d[map[yy][xx]] && !check[yy][xx]) {
                check[yy][xx] = true;
                d[map[yy][xx]] = true;
                if(direction==i) {
                    count = Math.max(count, go(start_x, start_y, xx, yy, i, changeCount, dessertCount+1));
                } else {
                    count = Math.max(count, go(start_x, start_y, xx, yy, i, changeCount+1, dessertCount+1));
                }
                check[yy][xx] = false;
                d[map[yy][xx]] = false;
            }
        }


        return count;
    }


}

/*

1
4
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5

1
5
8 2 9 6 6
1 9 3 3 4
8 2 3 3 6
4 3 4 4 9
7 4 6 3 5


1
20
11 34 7 49 59 88 79 12 63 38 13 27 9 70 97 92 86 95 84 18
11 84 39 44 86 86 59 52 61 97 81 94 92 78 32 7 5 62 41 75
15 61 71 27 3 4 79 51 95 99 73 27 75 31 4 7 15 51 50 16
6 81 32 61 75 24 36 26 57 55 52 15 35 44 30 25 2 54 12 25
42 4 66 1 23 44 1 7 63 27 82 70 40 45 4 3 12 35 11 85
97 55 69 49 34 79 37 69 89 66 85 22 23 88 24 79 1 48 85 72
4 67 23 3 27 18 37 61 7 68 88 80 35 21 42 88 38 10 81 84
78 86 21 50 46 13 50 9 54 3 1 94 85 75 80 45 31 100 29 70
9 59 7 48 81 82 43 68 90 37 26 41 84 31 58 42 4 96 86 20
22 4 49 94 74 42 6 38 84 90 29 95 84 36 18 4 10 34 71 26
46 43 7 88 18 21 96 57 3 72 52 83 50 53 56 51 19 50 57 6
15 30 88 26 49 10 6 12 98 81 47 88 82 2 68 85 62 12 92 88
100 31 5 15 76 84 39 10 52 61 28 12 50 22 35 85 1 83 2 76
17 27 83 45 18 4 95 37 23 96 58 49 36 47 13 10 41 38 37 6
22 92 59 68 51 15 65 88 18 69 40 49 7 11 78 14 95 94 45 27
13 36 33 22 29 49 11 10 50 91 15 71 87 83 63 26 76 89 28 9
98 9 96 58 72 79 28 9 63 67 85 16 40 66 46 47 17 85 16 99
42 87 28 97 60 89 92 90 51 60 96 22 51 95 55 44 16 9 51 69
27 45 53 43 45 52 12 90 86 91 47 39 84 9 21 77 69 56 5 69
99 47 66 91 71 2 9 26 43 54 52 30 4 94 97 92 2 67 12 85
 */
