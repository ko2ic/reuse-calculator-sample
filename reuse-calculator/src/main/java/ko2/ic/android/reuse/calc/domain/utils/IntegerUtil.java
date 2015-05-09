package ko2.ic.android.reuse.calc.domain.utils;

import ko2.ic.android.common.datatype.utils.StringUtil;

/**
 * {@link Integer}のユーティリティ。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 125 $
 */
public final class IntegerUtil {
    /**
     * コンストラクタ<br>
     */
    private IntegerUtil() {
    }

    /**
     * {@link Integer}に変換する。<br>
     * @param src 変換元
     * @return 変換後の値
     */
    public static Integer convertToInteger(String src) {
        if (StringUtil.isEmpty(src)) {
            return null;
        }

        return Integer.valueOf(src);
    }

    /**
     * 文字列に変換する。<br>
     * @param src 変換元
     * @return 変換後の値
     */
    public static String convertToString(Integer src) {
        if (src == null) {
            return "";
        }

        return Integer.toString(src);
    }
}
