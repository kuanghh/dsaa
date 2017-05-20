package com.khh.list.practies;

/**
 * Created by Administrator on 2017/5/19.
 * josephus问题是下面的游戏:
 * N 个人编号从1到N,围城一个圆圈，
 * 从一号开始传递一个热土豆，经过M次传递后拿着热土豆的人将被清除离座，围坐的圆圈缩紧，由坐在被清除的人后面的人拿起热土豆继续游戏，
 * 剩下的人为赢家。
 *
 * 例如如果 M = 0，N = 5那么5号获胜
 * 如果M = 1，N = 5 ,那么3号获胜，被清除的人的顺序是2，4，1，5
 */
public class Practies3_6 {

    public static void main(String[] args) {
        int winner = calc_josephus(6, 1);
        System.out.println(winner);
    }

    /**
     * N个人，传递M次
     * @param N
     * @param M
     * @return
     */
    public static int calc_josephus(int N,int M){
        /**
         * 用一个二维数组，第一维表示当前人的位置，第二维表示这个人是否出局了
         * 先初始化，每个人都有晓
         */
        Integer[][] a = new Integer[N][1];
        for(int i = 0; i < N;i++){
            a[i][0] = 1;
        }
        /**
         * 游戏开始
         * 用count来记录还有多少人在场
         * 用trans来记录每轮传递还需要经过多少人的手,经人手数 = 传递数 + 1
         */
        int count = N;
        int trans = M+1;
        int i = 0;

        for(i = 0; count > 1; i++){
            if(i >= N){
                i = 0;
                while(a[i][0] == 0){
                    i++;
                }
            }
            if(a[i][0] != 0){
                trans -- ;
            }
            if(trans == 0){
                a[i][0] = 0;
                System.out.println("第" + (i+1) + "出局");
                trans = M+1;
                count --;
            }
        }
        int winner = 0;
        while(a[winner][0] != 1){
            winner++;
        }

        return winner + 1;
    }
}
