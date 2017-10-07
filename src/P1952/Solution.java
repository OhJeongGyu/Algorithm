package P1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int T;
    static int[] prices;
    static int[] plans;

    public static void main(String args[]) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());

        for(int t = 1 ; t <= T ; t++) {
            prices = new int[4];
            plans = new int[12];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int i = 0 ; i < 4 ; i++) {
                prices[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int i = 0 ; i < 12 ; i++) {
                plans[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            int min = prices[3];
            min = Math.min(min, go(0, 11));
            System.out.println("#" + t + " " + min);

        }
    }

    static int go(int x, int y) {
        if(x == y) return Math.min(prices[1], prices[0] * plans[x]);

        int count = Integer.MAX_VALUE;
        if(y - x < 3) count = prices[2];

        for(int i = x ; i < y ; i++) {
            count = Math.min(count, go(x, i) + go(i+1, y));
        }

        return count;
    }
}
