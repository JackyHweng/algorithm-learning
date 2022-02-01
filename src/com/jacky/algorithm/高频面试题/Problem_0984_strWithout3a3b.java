package com.jacky.algorithm.高频面试题;

/**
 * <p>
 * 984. 不含 AAA 或 BBB 的字符串
 *
 * 给定两个整数 a和 b，返回任意字符串 s，要求满足：
 *
 * s的长度为 a + b，且正好包含a个 'a'字母与b个 'b'字母；
 * 子串'aaa'没有出现在 s中；
 * 子串'bbb' 没有出现在 s中。
 *
 * </p>
 *
 * 在A和B都>0的时候，如果A>B，那么往结果中加入"aab"，如果B>A，那么往结果中加入"bba"，如果A=B，那么往结果中加入"ab"，
 * 处理完每一步之后记得A和B要减去相应的数量。最后如果A有剩余，那么往结果中加入"a"，如果B有剩余，那么往结果中加入"b"。
 * 这种方法不会出现"aaa"或者"bbb"的情况，因为如果一开始A>B，那么几次循环之后，
 * 一定有A=B（然后一直加入"ab"，直到A和B等于0）或者A>0&&B=0（然后加入A个"a"即可，题目保证输入一定有解，所以剩余的A一定<=2，而因为前面一直A>B，结果中加入的都是"aab"，以"b"结尾，所以不会出现"aaa"的情况）。
 *
 * @author: HuangJiaJie
 * @create: 2022/1/29
 **/
public class Problem_0984_strWithout3a3b {

    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();
        while( A > 0 && B > 0){
            if( A > B){
                sb.append("aab");
                A-=2;
                B-=1;
            }else if(A == B){
                sb.append("ab");
                A-=1;
                B-=1;
            }else if(B > A){
                sb.append("bba");
                A-=1;
                B-=2;
            }

        }
        while(A > 0){
            sb.append("a");
            A--;
        }
        while(B > 0){
            sb.append("b");
            B--;
        }
        return sb.toString();
    }

}
