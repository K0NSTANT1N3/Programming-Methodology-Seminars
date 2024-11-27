package section;

import acm.program.ConsoleProgram;

import java.util.StringTokenizer;

import static java.lang.Character.isDigit;

public class MakeMax extends ConsoleProgram {

    public void run() {
        String s = "129 67 84 123 22";

        String ans = evaluate(s);

        println(ans);
    }

    private String getLeftNum(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                break;
            }
            res = res + s.charAt(i);
        }
        return res;
    }

    private String greater(String a, String b) {
        int al = a.length();
        int bl = b.length();

        String minStr = al < bl ? a : b;
        String maxStr = al >= bl ? a : b;

        for (int i = 0; i < maxStr.length(); i++) {
            int idxMin = i % minStr.length();
            int idxMax = i;

            char charMin = minStr.charAt(idxMin);
            char charMax = maxStr.charAt(idxMax);

            if (charMin > charMax) {
                return minStr;
            }
            if (charMin < charMax) {
                return maxStr;
            }
        }

        return a;
    }

    private String evaluate(String s) {
        String ans = "";
        int quantity = getQuantity(s);

        String indexes = getIndexes(s);

        ans = evalHelper(s, indexes, quantity);
        return ans;
    }

    private String evalHelper(String s, String indexes, int quantity) {
        if (quantity == 1) {
            return s;
        } else if (quantity == 0) {
            return "";
        }

        int mid = quantity / 2;

        int midIndexInS = 0;
        int spaceCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (spaceCount == mid) break;
            if (s.charAt(i) == ' ') spaceCount++;
            midIndexInS = i;
        }

        String s1 = s.substring(0, midIndexInS);
        String s2 = s.substring(midIndexInS + 1);

        int q1 = getQuantity(s1);
        int q2 = getQuantity(s2);

        String sorted1 = evalHelper(s1, indexes, q1);
        String sorted2 = evalHelper(s2, indexes, q2);

        String ans = "";

        int maxLn = Math.max(sorted1.length(), sorted2.length());

        StringTokenizer strtkn1;
        StringTokenizer strtkn2;
        for (; sorted1.length() > 0  && sorted2.length() > 0;) {
            strtkn1 = new StringTokenizer(sorted1, " ");
            strtkn2 = new StringTokenizer(sorted2, " ");

            //cur 1 equals string inside strtkn1
            String cur1 = getLeftNum(sorted1);
            String cur2 = getLeftNum(sorted2);

            String greatStr = greater(cur1, cur2);

            if (ans.length() != 0) ans += ' ';
            ans += greatStr;

            if (cur1.equals(greatStr)) {
                sorted1 = cutLeft(sorted1);
            } else {
                sorted2 = cutLeft(sorted2);
            }
        }

        if(sorted1.length() > 0){
            ans += ' ';
            ans += sorted1;
        }
        else if(sorted2.length() > 0){
            ans += ' ';
            ans += sorted2;
        }

        return ans;
    }

    private String cutLeft(String sorted1) {
        String ans = "";
        int i;
        for (i = 0; i < sorted1.length(); i++) {
            if (sorted1.charAt(i) == ' ') {
                break;
            }
        }
        if(i >= sorted1.length()) return "";
        return sorted1.substring(i + 1);
    }

    // 123 984

    private String intToString(int x) {
        if (x <= 9) {
            String str = "";
            str += (char) ('0' + x);
            return str;
        }

        int part = x / 10;
        int last = x % 10;

        String partStr = intToString(part);
        partStr += (char) ('0' + last);

        return partStr;
    }

    private String getIndexes(String s) {
        String ans = "0";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                ans += ' ';
                String curIndex = intToString(i + 1);
                ans += curIndex;
            }
        }
        return ans;
    }

    private int getQuantity(String s) {
        int ans = 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') ans++;
        }
        return ans;
    }
}
