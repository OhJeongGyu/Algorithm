package P2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

    static int T;

    /**
     * N : 셀 의 갯수
     */
    static int N;

    /**
     * M : 격리 시
     */
    static int M;

    /**
     * K : 군집 갯수
     */
    static int K;

    static int map[][];
    static int temp_map[][];
    static HashMap<Integer, Point> hashMap;

    static int directions[][] = {
            {0,-1},
            {0,1},
            {-1,0},
            {1,0} };

    static int count;
    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1 ; t <= T ; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());
            map = new int[N][N];
            hashMap = new HashMap<>();

            count = 0;
            for(int i = 1 ; i <= K ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int y = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int size = Integer.parseInt(stringTokenizer.nextToken());
                int direction = Integer.parseInt(stringTokenizer.nextToken())-1;

                map[y][x] = i;
                hashMap.put(i, new Point(size, direction, size));
            }

            for(int m = 0 ; m < M; m++) {

                temp_map = new int[N][N];

                for(int i = 0 ; i < N ; i++) {
                    for(int j = 0 ; j < N ; j++) {
                        if(map[i][j] > 0 ) { // 약품

                            Point p = hashMap.get(map[i][j]);

                            int xx = j + directions[p.direction][0];
                            int yy = i + directions[p.direction][1];
                            if(xx==0 || xx==N-1 || yy==0 || yy==N-1) {
                                long new_size = (long)Math.floor(p.size/2.0);
                                if(new_size>0) {
                                    temp_map[yy][xx] = map[i][j];
                                    int d = -1;
                                    if (p.direction == 0) {
                                        d = 1;
                                    } else if (p.direction == 1) {
                                        d = 0;
                                    } else if (p.direction == 2) {
                                        d = 3;
                                    } else if (p.direction == 3) {
                                        d = 2;
                                    }
                                    hashMap.put(map[i][j], new Point(new_size, d, new_size));
                                }
                            } else if(temp_map[yy][xx] > 0) { // 다른게 있음
                                Point temp_p = hashMap.get(temp_map[yy][xx]);
                                temp_map[yy][xx] = map[i][j];
                                if(temp_p.direction_count > p.direction_count) {
                                    hashMap.put(temp_map[yy][xx],new Point(temp_p.size + p.size, temp_p.direction, temp_p.size));
                                }else {
                                    hashMap.put(temp_map[yy][xx],new Point(temp_p.size + p.size, p.direction, p.size));
                                }
                            } else {
                                temp_map[yy][xx] = map[i][j];
                                hashMap.put(temp_map[yy][xx], new Point(p.size, p.direction, p.direction_count));
                            }

                            map[i][j] = 0;
                        }
                    }
                }
                hashMap.forEach((integer, point) -> {
                    if(point.direction_count!=point.size) hashMap.put(integer, new Point(point.size, point.direction, point.size));
                });
                map = temp_map;
//                map = Arrays.copyOfRange(temp_map,0 , temp_map.length);
//                for(int i = 0 ; i < N ; i++) {
//                    for(int j = 0 ; j < N ; j++) {
////                        System.out.print(map[i][j] + " ");
//                        Point p = hashMap.get(map[i][j]);
//                        if(p == null ) System.out.print(0 + " ");
//                        else System.out.print(p.size+ " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }

            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++) {
                    if(map[i][j]>0) {
//                        System.out.println(hashMap.get(map[i][j]).size);
                        count+=hashMap.get(map[i][j]).size;
                    }
                }
            }
            System.out.println("#"+t + " " + count);
        }
    }

    static class Point {
        long size;
        int direction;
        long direction_count;
        public Point(long size, int direction, long direction_count) {
            this.size = size;
            this.direction = direction;
            this.direction_count = direction_count;
        }
    }
}


/*

1
10 24 36
6 6 923 3ㅎ
7 6 910 2
2 1 278 2
2 5 164 3
8 6 505 4
2 8 970 1
1 1 85 2
1 6 194 1
5 3 572 1
7 4 611 4
6 2 565 4
1 3 609 4
1 7 74 2
6 5 573 4
5 1 31 3
7 7 779 3
7 1 391 3
8 5 364 3
7 8 474 1
5 6 547 3
2 6 195 2
3 7 754 4
1 8 912 1
3 8 415 1
5 8 434 4
5 7 958 4
2 7 700 3
4 5 974 1
4 7 376 4
3 1 111 1
3 6 486 1
8 4 545 1
5 2 237 3
4 2 850 2
2 4 793 2
6 3 877 2

 */