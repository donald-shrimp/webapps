import java.util.Hashtable;

public class Bank {
    private Hashtable<String, Account> customer; /* 口座リスト */

    public Bank() { /* 口座リストを初期化する */
        customer = new Hashtable<String, Account>();
        // 必要な処理があれば追加する
    }

    public int open(String name /* 口座名 */) {
        Account man = customer.get(name);
        if (man == null) {
            man = new Account(name);
            customer.put(name, man);
            return 0;
        } else {
            return -7;
        }
    } /* 口座開設 */

    public int close(String name /* 口座名 */) {
        Account man = customer.get(name);
        int money;
        if (man == null) {
            return -7;

        } else {
            money = man.showBalance();
            if (money == 0) {
                customer.remove(name);
                return 0;
            } else {
                return -1;
            }
        }
    } /* 口座解約 */

    public int deposit(String name /* 口座名 */, int amount /* 預金額 */) {
        Account man = customer.get(name);
        if (man == null) {
            return -7;
        } else {
            return man.deposit(amount);

        }
    } /* 預金 */

    public int withdraw(String name /* 口座名 */, int amount /* 引き出し額 */) {
        Account man = customer.get(name);
        if (man == null) {
            return -7;
        } else {
            return man.withdraw(amount);

        }
    } /* 払い戻し */

    public int showBalance(String name /* 口座名 */) {
        Account man = customer.get(name);
        if (man == null) {
            return -7;
        } else {
            return man.showBalance();

        }
    } /* 残高照会 */

    public int deposit(String name /* 口座名 */, String amount /* 預金額 */) {
        int money;
        try {
            money = Integer.parseInt(amount);
            return deposit(name, money);
        } catch (NumberFormatException e) {
            if (deposit(name, 0) == -7) {
                return -7;
            }
            return -4;
        }
    } /* 預金 */

    public int withdraw(String name /* 口座名 */, String amount /* 引き出し額 */) {
        int money;
        try {
            money = Integer.parseInt(amount);
            return withdraw(name, money);
        } catch (NumberFormatException e) {
            if (withdraw(name, 0) == -7) {
                return -7;
            }
            return -4;
        }
    } /* 払い戻し */
}
