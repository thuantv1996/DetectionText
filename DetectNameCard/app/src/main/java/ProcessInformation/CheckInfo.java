package ProcessInformation;

/**
 * Created by thuan on 3/30/2018.
 */

public class CheckInfo {
    private static boolean isNumber(String s) {
        int count = 0;
        String str = s.replace(" ", "");
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '+':
                case '-':
                case '.':
                case '(':
                case ')':
                    count += 1;
                    break;
            }
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                count += 1;
            }
        }
        return count / str.length() >= 60 ? true : false;
    }

    public static boolean isEmail(String str) {
        if (str.split("@").length > 1)
        {
            return true;
        }
        return false;
    }

    public static boolean isWebsite(String str) {
        if (str.toLowerCase().split("www.").length > 1) {
            return true;
        }
        return false;
    }

    public static boolean isNameBusiness(String str) {
        if (str.toLowerCase().indexOf("ltd") != -1) {
            return true;
        }
        return false;
    }

    public static boolean isNumberPhone(String str) {
        if (!isNumber(str)) {
            return false;
        }
        return true;
    }

    public static boolean isFax(String str) {
        if (!isNumber(str)) {
            return false;
        }
        if (str.toLowerCase().indexOf("fax") != -1) {
            return true;
        }
        if (str.toLowerCase().charAt(0) == 'f') {
            return true;
        }
        return false;
    }
}
