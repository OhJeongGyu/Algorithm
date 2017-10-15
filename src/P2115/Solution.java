package P2115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {


    /**
     * T : 테스트 케이스 수
     */
    static int T;

    /**
     * N : 크기
     */
    static int N;

    /**
     * M : 선택 가능 벌통 크기
     */
    static int M;

    /**
     * C : 채취 가능 꿀 수
     */
    static int C;

    static int map[][];

    static int dist[][];


    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1 ; t <= T ; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[N][N];
            dist = new int[N][N];

            for(int i = 0 ; i < N ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int max = 0;
            int max_x = 0;
            int max_y = 0;

            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    dist[i][j] = go(j, j+M, i, 0);
                    if(dist[i][j] > max) {
                        max = dist[i][j];
                        max_x = j;
                        max_y = i;
                    }
                }
            }

            int second = 0;
            for(int i = 0 ; i< N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(dist[i][j] > second) {
                        if(i == max_y && (j >= max_x && j <= max_x+M)) {
                            continue;
                        }
                        second = dist[i][j];
                    }
                }
            }


            System.out.println(max + second);
        }


    }

    private static int go(int x, int x_end, int y, int count) {
        if(count > M) return map[y][x];
        int max = 0;
        for(int i = x+1 ; i <= x_end ; i++) {
            if(i > N) continue;
            int temp = go(i, x_end, y, count + 1) + map[y][x];
            if(temp<=C) {
                max = Math.max(max, temp);
            }


        }
        for(int i = x+1 ; i <= x_end ; i++) {
            if(i > N) continue;
            int temp = go(i, x_end, y, count);
            if(temp<=C) {
                max = Math.max(max, temp);
            }
        }
        return max;

    }

}
