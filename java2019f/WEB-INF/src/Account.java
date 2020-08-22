public class Account {
    private String name; /* 口座名 */
    private int balance; /* 口座の残高 */

    public Account(String myName /* 口座名 */) {
        this.name = myName;
        balance = 0;
    } /* コンストラクタ */

    public int deposit(int amount/* 預金額 */) {
        if (amount <= 0) {
            return -3;
        }
        balance += amount;
        return 0;

    } /* 預金 */

    public int withdraw(int amount/* 出金額 */) {
        if (amount <= 0) {
            return -3;
        }
        balance -= amount;
        if (balance < 0) {
            balance += amount;
            return -1;
        }
        return 0;
    } /* 払い戻し */

    public int showBalance() {
        return balance;
    } /* 残高照会 */
}