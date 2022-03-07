package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 一个数转换为7进制
 * </p>
 *
 * @author: HuangJiaJie
 * @create: 2022/3/7
 **/
public class Problem_0504_ConvertToBase7 {

    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        boolean negative = num < 0 ;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(num % 7);
            num /= 7;
        }
        if(negative){
            sb.append("-");
        }

        return sb.reverse().toString();
    }
}
