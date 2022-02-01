## KMP算法


假设字符串str长度为N，字符串match长度为M，M <= N

想确定str中是否有某个子串是等于match的。

时间复杂度O(N)

## KMP算法核心

暴力方法的本质： str,match
str 从0 开始和 match 0 位置开始匹配，假设匹配 str[X】 ！= match[Y]
那么就重新回退到开始位置，并且str的开始位置+1，也就是 str[i+1]和match[0]开始匹配


Kmp也暴力解法思路差不多

不过做了流程的优化，str 从0 开始和 match 0 位置开始匹配，假设匹配 str[X】 ！= match[Y]
此时，计算Match[Y]位置以前的字符串最大匹配长度，假设是5，那么Str[X] 的X 不动，
Match[5] 和 Str[X] 开始往下配

### 举例子

s1 aabaacaabaac 

s2 aabaacaabaat

s1[14] != s2[14] , 此时s2 14位置的最长前缀和后缀匹配长度为5
也就是 s2[0...4] == s2[9...13] , 那么s1[13] 和 s2[5]继续匹配
因为 s1[0..13] 和 s2[0...13]相等，
说明 s1[9...13] 和 s2[9...13]也相等
s2[0...5] == s2[9...13]
s1[9...13] == s2[0...5] 那就可以不用重复匹配这一段了
直接匹配 s1[6...N] 和 S1[14...N]

为什么s1[6...N] 直接从14位置开始不是从1开始呢？
因为

也是看以i位置匹配是否可以看出，i+1...N

那么i的指标是什么？

i位置的之前的最大前缀和最大后缀的字符串匹配相等长度

str = aabaabcaabaabcZ

假设match字符 aabaabcaabaabcs

next = [-1 0 1 0 1 2 3 ...]

1）如何理解next数组

2）如何利用next数组加速匹配过程，优化时的两个实质！（私货解释）

s aabaaba <-k位置

m aabaabk <- J位置

假设m生成的next数组为 [-1,0,1,1,1,2,3]

可以知道J位置的m字符最大长缀和后缀为3


1. 获取当前位置 next[J] = index, 那么m[J-index] 和 S[K] 继续比对

2. 如果不成功就继续，或者当前位置的next数组的值，next[index] = i > 0, 在执行步骤一

3. 如果 next[index] == -1 , 那么就从 s[K+1],m[0] 位置开始匹配


## 题目

给定两棵二叉树的头节点head1和head2

想知道head1中是否有某个子树的结构和head2完全一样


https://leetcode-cn.com/problems/implement-strstr/

