package P2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int N, M;

    static int map[][];

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(bufferedReader.readLine());

        for(int t = 1 ; t <= T ; t++) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[N][N];
            for(int i = 0 ; i < N ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int max = 0;
            for(int k = 1 ; k <= N+1 ; k++) {
                for(int i = -N+1 ; i < N ; i++) {
                    for(int j = -N+1 ; j < N ; j++) {
                        max = Math.max(max,go(k, j, i));
                    }
                }
            }

            System.out.println("#"+t +" " + max);
        }
    }

    static int go(int k, int start_x, int start_y) {

        int x = start_x;
        int y = start_y;

        int max_dist = k * k + (k - 1) * (k - 1);
        int dist = 0;
        int count = 0;

        int index = 0;


        for (int i = y; i < y + k; i++) {
            for (int j = x - index; j <= x + index; j++) {
//                System.out.println(y + " : " + x + " : " + i + " : " + j);
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    if (map[i][j] == 1) {
                        dist += M;
                        count++;
                    }
                }
            }
//            System.out.println();
            index++;
        }
        index = k-2;

        for (int i = y + k ; i < y + 2 * k - 1; i++) {
            for (int j = x - index; j <= x + index; j++) {
//                System.out.println(y + " : " + x + " : " + i + " : " + j);
                if (i >= 0 && i < N && j >= 0 && j < N) {
                    if (map[i][j] == 1) {
                        dist += M;
                        count++;
                    }
                }
            }
//            System.out.println();
            index--;
        }
        if (dist >= max_dist) return count;
        return 0;

    }

}


/*

1
8 3
0 0 0 0 0 1 0 0
0 1 0 1 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 1 1 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 1 0 1 0
1 0 0 0 0 0 0 0

 */