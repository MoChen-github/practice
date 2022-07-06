package com.chen.huaweiOD;

import java.util.*;

/**
 * 1.【寻找身高相近的小朋友】
 * 【寻找身高相近的小朋友】小明今年升学到小学一年级，来到新班级后发现其他小朋友们身高参差不齐，然后就想基于各小朋友和自己的身高差对他们进行排序，请帮他实现排序。
 *
 * 输入描述：
 *
 * 第一行为正整数H和N，0<H<200，为小明的身高，0<N<50，为新班级其他小朋友个数。第第二行为N个正整数H1-HN，分别是其他小朋友的身高，取值范围0<N<50，为新班级其他小朋友个数。  第二行为N个正整数H1-HN，分别是其他小朋友的身高，取值范围0><Hi<200（1<=i<=N），且N个正整数各不相同。
 *
 * 输出描述：
 *
 * 输出排序结果，各正整数以空格分割。和小明身高差绝对值最小的小朋友排在前面，和小明身高差绝对值最大的小朋友排在最后，如果两个小朋友和小明身高差一样，则个子较小的小朋友排在前面。
 *
 * 示例1：
 *
 * 输入
 *
 * 100 10
 *
 * 95 96 97 98 99 101 102 103 104 105
 *
 * 输出
 *
 * 99 101 98 102 97103 96 104 95 105
 */
public class SortByHeightDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int H = scanner.nextInt();
        int N = scanner.nextInt();
        ArrayList<Child> children = new ArrayList<>();
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            int high = scanner.nextInt();
            Child child = new Child(high);
            child.setDifference(Math.abs(H - child.getHigh()));
            children.add(child);
        }


        for (int i = 0; i < children.size() - 1; i++) {
            for (int j = 0; j < children.size() - i -1; j++) {

                Child temp;
                if (children.get(j).getDifference() > children.get(j+1).getDifference()) {
                    temp = children.get(j);
                    children.set(j, children.get(j+1));
                    children.set(j+1, temp);
                }
            }
        }

        for (int i = 0; i < children.size(); i++) {
            result[i] = children.get(i).getHigh();
        }

        for (int j : result) {
            System.out.println(j);

        }

    }
    static class Child{
        private int high;
        private int difference;

        public Child(int high) {
            this.high = high;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getDifference() {
            return difference;
        }

        public void setDifference(int difference) {
            this.difference = difference;
        }

        @Override
        public String toString() {
            return "Child{" +
                    "high=" + high +
                    ", difference=" + difference +
                    '}';
        }
    }
}
