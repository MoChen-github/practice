package com.chen.huaweiOD;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 标题：日志排序 | 时间限制：1秒 | 内存限制：262144K | 语言不限
 * 运维工程师采集到某产品现网运行一天产生的日志N条，现需根据日志时间按时间先后顺序对日志进行排序。
 * 日志时间格式为：
 * H:M:S.N
 * H表示小时(0-23)，M表示分钟(0-59)，S表示秒(0-59)，N表示毫秒(0-999)
 * 时间可能并没有补齐，也就是说: 01:01:01.001，也可能表示为1:1:1.1
 *
 * 输入描述:
 *
 * 第一行输入一个整数N，表示日志条数，1<=N<=100000
 * 接下来N行输入N个时间
 *
 * 输出描述:
 *
 * 按时间升序排序之后的时间
 * 如果有两个时间表示的时间相同，则保持输入顺序
 */
public class LogSorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        LinkedList<String[]> times = new LinkedList<>();
        for (int i = 0; i < N; ) {
            String[] time = scanner.nextLine().replace(".", ":").split(":");
            if (!time[0].equals("")) {
                i++;
                times.add(time);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                int s = 0;
                compare(j, times, s);
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(times.get(i)[0] + ":" + times.get(i)[1] + ":" + times.get(i)[2] + "." + times.get(i)[3]);
        }
    }

    public static void change(int i, LinkedList<String[]> times) {
        String[] temp = times.get(i);
        times.set(i, times.get(i + 1));
        times.set(i + 1, temp);
    }


    public static int compareNums(int i, LinkedList<String[]> times, int s) {
        return Integer.compare(Integer.parseInt(times.get(i)[s]), Integer.parseInt(times.get(i + 1)[s]));
    }

    public static void compare(int j, LinkedList<String[]> times, int s) {
        switch (compareNums(j, times, s)) {
            case 1:
                change(j, times);
                break;
            case 0:
                s++;
                compare(j, times, s);
        }
    }
}
