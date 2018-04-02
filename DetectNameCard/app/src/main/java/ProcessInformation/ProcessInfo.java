package ProcessInformation;

/**
 * Created by thuan on 3/30/2018.
 */

public class ProcessInfo {

    public static String getNameBusiness(String str) {
        if (CheckInfo.isNameBusiness(str)) {
            return str;
        }
        return null;
    }

    public static String getNumberPhone(String str) {
        if (!CheckInfo.isNumberPhone(str)) {
            return null;
        }
        if (str.toLowerCase().charAt(0) == 'f') {
            return null;
        }
        String numberPhone = "";
        boolean isbegin=false;
        for (int i = 0; i < str.length(); i++) {

            switch (str.charAt(i)) {
                case '+':
                case '-':
                case '.':
                case '(':
                case ')':
                    if(!isbegin) {
                        isbegin = true;
                    }
                    numberPhone += str.charAt(i);
                    continue;
            }
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                if(!isbegin) {
                    isbegin = true;
                }
                numberPhone += str.charAt(i);
                continue;
            }
            if(str.charAt(i)==' ')
            {
                if(isbegin)
                {
                    numberPhone += str.charAt(i);
                    continue;
                }
            }
            if(isbegin)
            {
                break;
            }
        }
        return numberPhone;
    }

    public static String getNumberFax(String str) {
        if (!CheckInfo.isFax(str)) {
            return null;
        }
        String numberPhone = "";
        boolean isbegin=false;
        int begin = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.toLowerCase().charAt(i)=='f')
            {
                begin=i;
                break;
            }
        }

        for (int i = begin; i < str.length(); i++) {

            switch (str.charAt(i)) {
                case '+':
                case '-':
                case '.':
                case '(':
                case ')':
                    if(!isbegin) {
                        isbegin = true;
                    }
                    numberPhone += str.charAt(i);
                    continue;
            }
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                if(!isbegin) {
                    isbegin = true;
                }
                numberPhone += str.charAt(i);
                continue;
            }
            if(str.charAt(i)==' ')
            {
                if(isbegin)
                {
                    numberPhone += str.charAt(i);
                    continue;
                }
            }
            if(isbegin)
            {
                break;
            }
        }
        return numberPhone;
    }

    public static String getEmail(String str)
    {
        String[] s = str.split(" ");
        for (int i=0;i<s.length;i++)
        {
            if(CheckInfo.isEmail(s[i]))
            {
                return s[i];
            }
        }
        return null;
    }

    public static String getWebsite(String str)
    {
        String[] s = str.split(" ");
        for (int i=0;i<s.length;i++)
        {
            if(CheckInfo.isWebsite(s[i]))
            {
                return s[i];
            }
        }
        return null;
    }

}
