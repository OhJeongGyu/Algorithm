package P2112;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    /**
     * Test Case
     */
    static int T;

    /**
     * D : 두께
     */
    static int D;

    /**
     * W : 가로 길이
     */
    static int W;

    /**
     * K : 합격 기준
     */
    static int K;

    /**
     * 보호필름 맵
     */
    static int map[][];


    public static void main(String args[]) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());

        for(int t = 1 ; t <= T ; t++) {

            int ans = Integer.MAX_VALUE;

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            D = Integer.parseInt(stringTokenizer.nextToken());
            W = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());

            map = new int[D][W];

            for(int i = 0 ; i < D ; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for(int j = 0; j < W ; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }


            for(int i = 0 ; i < D ; i++) {
                ans = Math.min(ans, go(map, i, -1));
                ans = Math.min(ans, go(map, i, 0)+1);
                ans = Math.min(ans, go(map, i, 1)+1);
            }

            System.out.println("#" + t + " " + ans);

        }

    }

    static int go(int map[][], int row, int num) {

        if(row >= D) {
            if(check()) return 0;
            else return 987654321;
        }

        int count = 987654321;

        if(num!=-1) {
            int[] temp = new int[W];
            for(int i = 0 ; i < W ; i++) {
                temp[i] = map[row][i];
            }
            Arrays.fill(map[row], num);
            count = Math.min(count, go(map, row+1, -1));
            count = Math.min(count, go(map, row+1, 0)+1);
            count = Math.min(count, go(map, row+1, 1)+1);
            for(int i = 0 ; i < W ; i++) {
                map[row][i] = temp[i];
            }
        } else {
            count = Math.min(count, go(map, row+1, -1));
            count = Math.min(count, go(map, row+1, 0)+1);
            count = Math.min(count, go(map, row+1, 1)+1);
        }

        return count;

    }

    static boolean check(){
        point : for(int i = 0 ; i < W ; i++) {

            int count = 0;
            int a = -1;
            int max = 0;
            for(int j = 0 ; j < D ; j++) {
                if(a==-1) {
                    a = map[j][i];
                    count = 1;
                    max = Math.max(max, count);
                    continue;
                }
                if(a == map[j][i]) {
                    count++;
                    max = Math.max(max, count);
                    if(max>=K) continue point;
                } else {
                    a = map[j][i];
                    count = 1;
                }
            }
            if(max < K ) return false;
        }
        return true;
    }
}
