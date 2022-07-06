package com.chen.practice;

/**
 * 使用位移实现除法
 */
public class Divide {
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        /*
            判断是否同号，同号则 结果为正数
         */
        boolean flag = (a < 0 && b < 0) || (a > 0 && b > 0);

        int dividend = Math.abs(a);
        int divisor = Math.abs(b);
        if (dividend < divisor) {
            return 0;
        }
        /*
            计算 结果的绝对值
         */
        int result = 0;
        int shift = 31;
        while (dividend >= divisor) {
            while (dividend < divisor << shift) {
                shift--;
            }
            dividend -= divisor << shift;
            result += 1 << shift;
        }

        return flag ? result : -result;
    }
}
