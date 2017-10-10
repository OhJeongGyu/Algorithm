package P2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int T;

    /**
     * N : 접수 창구 수
     */
    static int N;


    /**
     * M : 정비 창구 수
     */
    static int M;

    /**
     * K : 고객 수
     */
    static int K;

    /**
     * A : 지갑 잃어버린 사람이 사용한 접수 창구 번호
     */
    static int A;

    /**
     * B : 지갑 잃어버린 사람이 사용한 접수 정비 창구 번호
     */
    static int B;

    /**
     * a_time : 접수 창구 걸리는 시간
     */
    static int a_time[];

    /**
     * b_time : 정비 창구 걸리는 시간
     */
    static int b_time[];

    /**
     * k_time : 고객 도착하는 시간
     */
    static int k_time[];

    static List<Integer> a_human_list;
    static List<Integer> b_human_list;

    public static void main(String args[]) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1 ; t <= T ; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());
            K = Integer.parseInt(stringTokenizer.nextToken());
            A = Integer.parseInt(stringTokenizer.nextToken())-1;
            B = Integer.parseInt(stringTokenizer.nextToken())-1;

            a_time = new int[N];
            b_time = new int[M];
            k_time = new int[K];

            int ans = -1;

            a_human_list = new ArrayList<>();
            b_human_list = new ArrayList<>();

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int i = 0 ; i < N ; i++) {
                a_time[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int i = 0 ; i < M ; i++) {
                b_time[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for(int i = 0 ; i < K ; i++) {
                k_time[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            A a = new A();
            B b = new B();
            PriorityQueue<Human> a_wait = new PriorityQueue<>((o1, o2) -> o1.number - o2.number);
            PriorityQueue<Human> b_wait = new PriorityQueue<>((o1, o2) -> o1.number - o2.number);

            for(int time = 0 ; time < K*2 ; time++) {


                while (!a_wait.isEmpty()) {

                    Human temp = a_wait.poll();
                    if(!a.add(temp)) {
                        a_wait.add(temp);
                        break;
                    }
                }


                while (!b_wait.isEmpty()) {

                    Human temp = b_wait.poll();
                    if(!b.add(temp)) {
                        b_wait.add(temp);
                        break;
                    }
                }

                for(int i = 0 ; i < K ; i++) {
                    if(k_time[i] == time) {
                        a_wait.add(new Human(i));
                    }
                }

                b.go();
                List<Integer> list = a.go();
                list.forEach(humanNumber -> b_wait.add(new Human(humanNumber)));

//                a.map.forEach((integer, human) -> System.out.println(integer + " : " + human.number + " : " + human.remain));
//                System.out.println();
//                b.map.forEach((integer, human) -> System.out.println(integer + " : " + human.number + " : " + human.remain));
//                System.out.println();
            }

            for(int num : b_human_list) {
                ans+=num;
            }


            System.out.println("#" + t + " " +ans);
        }
    }


    static class A {
        HashMap<Integer, Human> map = new HashMap<>();

        public boolean add(Human human) {
            for(int i = 0 ; i < N ; i++) {
                if(!map.containsKey(i)) {
                    Human temp = new Human(human.number);
                    temp.remain = a_time[i];
                    map.put(i, temp);
                    if(i == A) {
                        a_human_list.add(human.number);
                    }
                    return true;
                }
            }
            return false;
        }

        public List<Integer> go(){
            List<Integer> remove_list = new ArrayList<>();

            for(int i = 0 ; i < N ; i++) {
                if(map.containsKey(i)) {
                    Human temp = map.get(i);
                    int index = temp.remain-1;
                    if(index==0) {
                        remove_list.add(i);
                    } else {
                        temp.remain = index;
                        map.put(i, temp);
                    }
                }
            }


//            map.forEach((integer, human) -> {
//                int i = human.remain-1;
//                if(i==0) {
//                    remove_list.add(integer);
//                } else {
//                    map.get(integer).remain = i;
//                }
//            });
            remove_list.forEach(i -> map.remove(i));
            return remove_list;
        }


    }

    static class B {
        HashMap<Integer, Human> map = new HashMap<>();

        public boolean add(Human human) {
            for(int i = 0 ; i < M ; i++) {
                if(!map.containsKey(i)) {
                    Human temp = new Human(human.number);
                    temp.remain = b_time[i];
                    map.put(i, temp);
                    if(a_human_list.contains(human.number) && i == B) {
                        b_human_list.add(human.number);
                    }
                    return true;
                }
            }
            return false;
        }

        public void go(){
            List<Integer> remove_list = new ArrayList<>();

            for(int i = 0 ; i < M ; i++) {
                if(map.containsKey(i)) {
                    Human temp = map.get(i);
                    int index = temp.remain-1;
                    if(index==0) {
                        remove_list.add(i);
                    } else {
                        temp.remain = index;
                        map.put(i, temp);
                    }
                }
            }
//            map.forEach((integer, human) -> {
//                int i = human.remain-1;
//                if(i==0) {
//                    remove_list.add(integer);
//                } else {
//                    map.get(integer).remain = i;
//                }
//            });
            remove_list.forEach(i->map.remove(i));
        }
    }

    static class Human {
        int number;
        int remain;

        public Human(int number) {
            this.number = number;
        }
    }


}
