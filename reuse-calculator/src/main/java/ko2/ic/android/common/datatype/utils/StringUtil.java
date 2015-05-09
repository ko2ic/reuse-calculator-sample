package ko2.ic.android.common.datatype.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字列ユーティリティ。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 108 $
 */
public final class StringUtil {
    /**
     * コンストラクタ<br>
     */
    private StringUtil() {
    }

    /**
     * 文字列が空かどうか。<br>
     * @param arg 文字列
     * @return 文字列が空の場合true。
     */
    public static boolean isEmpty(String arg) {
        return (arg == null || arg.length() == 0);
    }

    /**
     * 文字列が空かどうか。<br>
     * @param arg 文字列
     * @return 文字列が空ではない場合true。
     */
    public static boolean isNotEmpty(String arg) {
        return !isEmpty(arg);
    }

    /**
     * 数値変換可能で、半角かどうか<br>
     * @param str 値
     * @return 数値変換可能で、半角の場合true
     */
    public static boolean isHalfWidthNumber(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        return matchRegexp(str, "^[\\+\\-]?[0-9]*\\.?[0-9]*$");
    }

    /**
     * 文字列が正規表現の値とmatchしているかどうか<br>
     * @param value 値
     * @param regexp 正規表現
     * @return 文字列が正規表現の値とmatchしている場合true
     */
    public static boolean matchRegexp(String value, String regexp) {
        if (regexp == null || regexp.length() <= 0) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    /**
     * 配列を指定文字列で結合する。<br>
     * @param arry 配列
     * @param with 指定文字列
     * @return 結合後の文字列
     */
    public static String join(String[] arry, String with) {
        StringBuffer stringBuffer = new StringBuffer();

        for (String string : arry) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(with);
            }
            stringBuffer.append(string);
        }

        return stringBuffer.toString();
    }
}
