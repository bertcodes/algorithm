package com.onewo.fifthspace.algorithm.array;

/**
 * 题目地址
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * 题目描述
 * 给你一个整数数组 prices，其中 prices[i] 是某只股票在第 i 天的价格。
 *
 * 如果你只能在某一天买入这只股票，并在未来的某一天卖出，求能获得的最大利润。
 *
 * 示例
 * 示例 1：
 *
 * code
 * 应用
 * 输入：prices = [7, 1, 5, 3, 6, 4]
 * 输出：5
 * 解释：在第 2 天（价格 = 1）买入，在第 5 天（价格 = 6）卖出，利润 = 6 - 1 = 5
 * 示例 2：
 *
 * code
 * 应用
 * 输入：prices = [7, 6, 4, 3, 1]
 * 输出：0
 * 解释：价格持续下跌，不买卖，最大利润 = 0
 * 约束条件
 * code
 * 应用
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 * 解法一：暴力解法（超时）
 * 思路
 * 枚举所有可能的买入和卖出组合，计算最大利润。
 *
 * 代码
 * java
 * 应用
 * public int maxProfit(int[] prices) {
 *     int maxProfit = 0;
 *     int n = prices.length;
 *
 *     for (int i = 0; i < n; i++) {           // 买入日
 *         for (int j = i + 1; j < n; j++) {  // 卖出日（必须晚于买入日）
 *             int profit = prices[j] - prices[i];
 *             maxProfit = Math.max(maxProfit, profit);
 *         }
 *     }
 *
 *     return maxProfit;
 * }
 * 复杂度分析
 * 时间复杂度： O(n²)
 * 空间复杂度： O(1)
 *
 * 问题
 * code
 * 应用
 * 当 n = 10^5 时：
 * n² = 10^10 次操作
 * 时间会超时！
 * 解法二：贪心算法（最优解）
 * 核心思想
 * 只遍历一次数组，记录两个值：
 *
 * 历史最低价格（minPrice）
 * 当前最大利润（maxProfit）
 * 贪心策略
 * code
 * 应用
 * 遍历每一天时：
 * 1. 如果今天价格比历史最低价低 → 更新历史最低价（买入点）
 * 2. 否则 → 计算今天卖出的利润，更新最大利润
 * 为什么是贪心？
 * code
 * 应用
 * 贪心算法：每一步都做出局部最优选择
 *
 * 本题的贪心选择：
 * 对每一天，假设它是卖出日：
 *    → 最好的买入点就是历史最低价
 *    → 因为这能保证卖出时利润最大
 *
 * 局部最优：每一天都选择历史最低价作为买入点
 * 全局最优：最终得到整个序列的最大利润
 * 代码实现
 * java
 * 应用
 * public int maxProfit(int[] prices) {
 *     int minPrice = Integer.MAX_VALUE;  // 历史最低价，初始设为最大值
 *     int maxProfit = 0;                 // 最大利润，初始为0
 *
 *     for (int price : prices) {
 *         // 更新历史最低价（贪心选择：记录最低点作为买入点）
 *         if (price < minPrice) {
 *             minPrice = price;
 *         }
 *         // 否则，计算今天卖出的利润
 *         else {
 *             int profit = price - minPrice;
 *             maxProfit = Math.max(maxProfit, profit);
 *         }
 *     }
 *
 *     return maxProfit;
 * }
 * 简化版（更简洁）
 * java
 * 应用
 * public int maxProfit(int[] prices) {
 *     int minPrice = Integer.MAX_VALUE;
 *     int maxProfit = 0;
 *
 *     for (int price : prices) {
 *         minPrice = Math.min(minPrice, price);           // 更新最低价
 *         maxProfit = Math.max(maxProfit, price - minPrice); // 更新最大利润
 *     }
 *
 *     return maxProfit;
 * }
 * 手动执行示例
 * 输入：[7, 1, 5, 3, 6, 4]
 * 天数	price	minPrice	price - minPrice	maxProfit	说明
 * 0	7	7	0	0	初始化，minPrice=7
 * 1	1	1	0	0	发现更低价格，minPrice=1
 * 2	5	1	4	4	利润4，更新maxProfit=4
 * 3	3	1	2	4	利润2，不更新
 * 4	6	1	5	5	利润5，更新maxProfit=5
 * 5	4	1	3	5	利润3，不更新
 * 最终答案：5
 *
 * 可视化图解
 * code
 * 应用
 * 价格走势图：
 *     7 |  ●
 *     6 |              ●        ●
 *     5 |        ●
 *     4 |                     ●
 *     3 |           ●
 *     2 |
 *     1 |     ● ← 最低买入点
 *     0 |_______________________
 *       0  1  2  3  4  5  (天)
 *                    ↑
 *             最高卖出点
 *
 * 最优策略：第1天买入（价格1），第4天卖出（价格6）
 * 最大利润：6 - 1 = 5
 * 为什么贪心算法正确？
 * 数学证明
 * 定理： 对于任意一天 i，如果在 [0, i] 范围内卖出，最优的买入点一定是 [0, i] 中的最低价。
 *
 * 证明：
 *
 * code
 * 应用
 * 设 [0, i] 范围内的最低价为 minPrice
 * 假设在第 j 天卖出（j <= i）
 *
 * 如果买入价格 > minPrice：
 *    利润 = prices[j] - 买入价格
 *         < prices[j] - minPrice
 *
 * 如果买入价格 = minPrice：
 *    利润 = prices[j] - minPrice（最大）
 *
 * 因此，对于任意卖出日，选择最低价买入能获得最大利润。
 * 贪心选择性质
 * code
 * 应用
 * 问题分解：
 * 原问题：在整个数组中找最大利润
 *
 * 贪心分解：
 * 对每一天 i：
 *    - 买入点：[0, i] 中的最低价
 *    - 卖出点：第 i 天
 *    - 利润：prices[i] - minPrice
 *
 * 最终答案：max(所有天的利润)
 *
 * 为什么正确？
 * 因为：
 * 1. 买入必须在卖出之前
 * 2. 对于第 i 天卖出，最优买入就是历史最低价
 * 3. 每一天都取最优，最终必然全局最优
 * 复杂度分析
 * 贪心算法
 * 时间复杂度： O(n)
 *
 * 只遍历一次数组
 * 每次操作都是 O(1)
 * 空间复杂度： O(1)
 *
 * 只用了两个变量 minPrice 和 maxProfit
 * 对比暴力解法
 * 解法	时间复杂度	空间复杂度	n=10^5时
 * 暴力	O(n²)	O(1)	超时
 * 贪心	O(n)	O(1)	✅ 通过
 * 边界情况测试
 * 测试用例1：空数组
 * java
 * 应用
 * 输入：[]
 * 输出：0
 * 原因：没有数据，无法买卖
 * 测试用例2：单元素
 * java
 * 应用
 * 输入：[5]
 * 输出：0
 * 原因：只有一天，无法买卖
 * 测试用例3：递减序列
 * java
 * 应用
 * 输入：[7, 6, 4, 3, 1]
 * 输出：0
 * 原因：持续下跌，不买卖
 * 测试用例4：递增序列
 * java
 * 应用
 * 输入：[1, 2, 3, 4, 5]
 * 输出：4
 * 原因：第0天买1，第4天卖5，利润=4
 * 测试用例5：全相同
 * java
 * 应用
 * 输入：[3, 3, 3, 3, 3]
 * 输出：0
 * 原因：价格不变，利润为0
 * 变形问题：多次买卖
 * 题目变化
 * 可以买卖多次，但同一时间只能持有一只股票。
 *
 * 贪心解法
 * java
 * 应用
 * public int maxProfitII(int[] prices) {
 *     int maxProfit = 0;
 *
 *     for (int i = 1; i < prices.length; i++) {
 *         // 只要明天比今天贵，就今天买明天卖
 *         if (prices[i] > prices[i - 1]) {
 *             maxProfit += prices[i] - prices[i - 1];
 *         }
 *     }
 *
 *     return maxProfit;
 * }
 * 示例
 * code
 * 应用
 * 输入：[7, 1, 5, 3, 6, 4]
 *
 * 买卖策略：
 * 第1天买1 → 第2天卖5 = 利润4
 * 第3天买3 → 第4天卖6 = 利润3
 *
 * 总利润 = 4 + 3 = 7
 * 总结
 * 贪心算法的核心
 * code
 * 应用
 * 关键点：
 * 1. 每一步都做出局部最优选择
 * 2. 记录历史最低价（买入点）
 * 3. 计算当前利润，更新最大值
 *
 * 为什么有效：
 * - 对于每一天卖出，最优买入就是历史最低价
 * - 遍历所有可能卖出日，取最大值
 * 解题模板
 * java
 * 应用
 * public int maxProfit(int[] prices) {
 *     // 1. 初始化
 *     int minPrice = Integer.MAX_VALUE;
 *     int maxProfit = 0;
 *
 *     // 2. 遍历
 *     for (int price : prices) {
 *         // 3. 更新状态
 *         minPrice = Math.min(minPrice, price);
 *         maxProfit = Math.max(maxProfit, price - minPrice);
 *     }
 *
 *     // 4. 返回结果
 *     return maxProfit;
 * }
 * 记忆口诀
 * code
 * 应用
 * 只遍历一遍
 * 记录最低价
 * 计算每一天利润
 * 最大即为答案
 *
 *  * 输入：prices = [7, 1, 5, 3, 6, 4]
 *  * 输出：5
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfitViolent(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            for(int j=i+1; j<prices.length; j++){
                minPrice = Math.min(minPrice, prices[i]);
                maxProfit =Math.max(maxProfit,prices[j]-minPrice);
            }
        }
        return maxProfit;
    }

    public int maxProfitGreedy(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit =Math.max(maxProfit,prices[i]-minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new BestTimeToBuyAndSellStock().maxProfitGreedy(prices));
    }
}
