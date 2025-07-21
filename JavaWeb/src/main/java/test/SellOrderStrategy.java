package test;
import java.util.*;

/**
 * SellOrderStrategy 用於將欲賣出總量（qty）拆分成多筆不同數量的委託單。
 * 此策略能降低大額單一委託對市場的衝擊，並使交易更不易被識別。
 */
public class SellOrderStrategy {
    private static final Random rand = new Random();

    /**
     * 拆分賣出數量的方法
     * @param qty 要分拆的總賣出數量
     * @return 拆單結果，每個元素是一筆掛單數量
     */
    public static List<Integer> distributeQty(int qty) {
        int[] qtys = {
            499,
            randBetween(200, 250),
            randBetween(100, 150),
            randBetween(50, 75),
            randBetween(25, 35),
            randBetween(12, 18),
            randBetween(6, 10),
            randBetween(3, 5),
            1
        };

        List<Integer> orders = new ArrayList<>();
        int remain = qty;

        for (int q : qtys) {
            int n = Math.max(remain / q - 1, 0);
            for (int i = 0; i < n; i++) {
                orders.add(q);
            }
            remain -= n * q;
        }
        if (remain > 0) orders.add(1);
        return orders;
    }

    private static int randBetween(int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }

    /**
     * 主程式：多組參數 Demo
     */
    public static void main(String[] args) {
        int[] testQtys = {45, 99, 199, 350, 499, 1234, 1567, 2567};
        for (int qty : testQtys) {
            List<Integer> result = distributeQty(qty);
            int sum = result.stream().mapToInt(Integer::intValue).sum();

            System.out.println("輸入總數量：" + qty);
            System.out.println("拆分後的賣單數量序列：" + result);
            System.out.println("實際拆分總和：" + sum);
            System.out.println("-----");
        }
    }
}
