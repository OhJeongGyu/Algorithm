package P1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int N, M;
    static int R, C;
    static int L;

    static boolean check[][];
    static int map[][];

    static int directions[][][] = {{ },
            {{1,0},{0,1},{-1,0},{0,-1}},
            {{0,1}, {0,-1}},
            {{1,0},{-1,0}},
            {{0,-1}, {1,0}},
            {{0,1}, {1,0}},
            {{-1,0},{0,1}},
            {{-1,0},{0,-1}}
        };

    static boolean out_directions[][] = {
            {false, true, false, true, false, false, true, true},
            {false, true, true, false, true, false, false, true},
            {false, true, false, true, true, true, false, false},
            {false, true, true, false, false, true, true, false}
    };

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1 ; t <= T ; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            R = Integer.parseInt(stringTokenizer.nextToken());
            C = Integer.parseInt(stringTokenizer.nextToken());
            L = Integer.parseInt(stringTokenizer.nextToken());

            check = new boolean[N][M];
            map = new int[N][M];

            for(int i = 0 ; i < N ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j = 0 ; j < M ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int count = 1;
            Queue<Point> q = new LinkedList<>();

            q.add(new Point(C, R, 1));
            check[R][C] = true;
            while (!q.isEmpty()) {
                Point p = q.poll();
                for(int direction[] : directions[map[p.y][p.x]]) {
                    int xx = p.x + direction[0];
                    int yy = p.y + direction[1];



                    if(xx>=0 && xx<M && yy>=0 && yy<N) {
                        if(!check[yy][xx] && p.time < L &&  canGo(direction[0], direction[1], map[yy][xx])) {
                            check[yy][xx] = true;
                            count++;
                            q.add(new Point(xx, yy, p.time+1));
                        }
                    }
                }
            }
            System.out.println("#"+t + " " +count);


        }


    }

    static boolean canGo(int x, int y, int direction) {
        if(x==1 && y==0) {
            return out_directions[0][direction];
        }

        if(x==0 && y==1) {
            return out_directions[1][direction];
        }

        if(x==-1 && y==0) {
            return out_directions[2][direction];
        }

        if(x==0 && y==-1) {
            return out_directions[3][direction];
        }

        return false;
    }


    static class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }



}
