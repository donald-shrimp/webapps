public class BankTest {
    public static void main(String[] args) {
        Bank ebi;
        int result;

        // open
        ebi = new Bank();

        result = ebi.open("ebibi");
        System.out.println("<open>");
        if (result == 0) {
            System.out.println("返り値0で正常です");
        } else {
            System.out.println("返り値-7で異常です");
        }

        result = ebi.open("ebibi");
        if (result == -7) {
            System.out.println("返り値-7で正常です");
        } else {
            System.out.println("返り値0で異常です");
        }

        System.out.println("<deposit>");
        result = ebi.deposit("ebibi", 10000);
        if (result == 0) {
            System.out.println("預金成功で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.deposit("unti", 10000);
        if (result == -7) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金成功で異常です\n");
        }

        System.out.println("EXdepo");

        result = ebi.deposit("ebibiあ", "10000");
        if (result == -7) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金成功で異常です\n");
        }

        result = ebi.deposit("ebibiあ", "i0000");
        if (result == -7) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金成功で異常です\n");
        }

        result = ebi.deposit("ebibiあ", "-10000");
        if (result == -7) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金成功で異常です\n");
        }

        result = ebi.deposit("ebibi", "一万");
        if (result == -4) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金成功で異常です\n");
        }

        result = ebi.deposit("ebibi", "1000");
        if (result == 0) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("預金失敗で異常です\n");
        }

        System.out.println("EXwith");
        result = ebi.withdraw("ebibi", "一万");
        if (result == -4) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("出金成功で異常です\n");
        }

        result = ebi.withdraw("ebibi", "1000");
        if (result == 0) {
            System.out.println("戻り値が" + result + "で成功です\n");
        } else {
            System.out.println("出金失敗で異常です\n");
        }

        result = ebi.deposit("ebibi", -1000);
        if (result == -3) {
            System.out.println("不正な金額で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        // 10
        result = ebi.showBalance("ebibi");
        if (result == 10000) {
            System.out.println("残高10000円で正常です\n");
        } else {
            System.out.println("残高が" + result + "円で誤りです\n");
        }

        result = ebi.withdraw("ebibi", 50000);
        if (result == -1) {
            System.out.println("残高不足で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.withdraw("ebibi", "50000");
        if (result == -1) {
            System.out.println("残高不足で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.withdraw("ebibi", -5000);
        if (result == -3) {
            System.out.println("不正な金額で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.withdraw("unti", 5000);
        if (result == -7) {
            System.out.println("不正な名前で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.withdraw("unti", "5000");
        if (result == -7) {
            System.out.println("不正な名前で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.withdraw("unti", "ps");
        if (result == -7) {
            System.out.println("不正な名前で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }
        result = ebi.withdraw("unti", "-5000");
        if (result == -7) {
            System.out.println("不正な名前で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.showBalance("ebibi");
        if (result == 10000) {
            System.out.println("残高10000円で正常です\n");
        } else {
            System.out.println("残高が" + result + "円で誤りです\n");
        }

        // 14~15
        result = ebi.withdraw("ebibi", 5000);
        if (result == 0) {
            System.out.println("引き出し成功で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        // 16
        result = ebi.showBalance("ebibi");
        if (result == 5000) {
            System.out.println("残高5000円で正常です\n");
        } else {
            System.out.println("残高が" + result + "円で誤りです\n");
        }

        // close
        System.out.println("<close>");

        result = ebi.close("ebibi");
        if (result == -1) {
            System.out.println("解約失敗で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        // 14~15
        result = ebi.withdraw("ebibi", 5000);
        if (result == 0) {
            System.out.println("引き出し成功で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }
        result = ebi.close("ebibi");

        if (result == 0) {
            System.out.println("解約成功で正常です\n");
        } else {
            System.out.println("戻り値が" + result + "で誤りです\n");
        }

        result = ebi.close("ebibi");
        if (result == 0) {
            System.out.println("解約成功で異常です\n");
        } else {
            System.out.println("戻り値が" + result + "で成功です\n");
        }

    }

}