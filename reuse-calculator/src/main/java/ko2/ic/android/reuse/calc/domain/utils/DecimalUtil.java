package ko2.ic.android.reuse.calc.domain.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import ko2.ic.android.common.datatype.utils.StringUtil;

/**
 * {@link BigDecimal}のユーティリティ。
 * @author $Author: ishii_ko $
 * @version $Revision: 125 $
 */
public final class DecimalUtil {
    /** フォーマット文字列のデフォルト */
    private static final String DEFUALT_FORMAT_PATTERN = "##.##";

    /**
     * コンストラクタ<br>
     */
    private DecimalUtil() {
    }

    /**
     * 文字列型に変換する。<br>
     * @param src 対象
     * @return 変換後の文字列
     */
    public static String convertToString(BigDecimal src) {
        if (src == null) {
            return "";
        }

        DecimalFormat format = new DecimalFormat(DEFUALT_FORMAT_PATTERN);
        return format.format(src);
    }

    /**
     * {@link BigDecimal}の変換する。<br>
     * @param src 変換元
     * @return 変換後の値
     */
    public static BigDecimal convertToBigDecimal(String src) {
        if (StringUtil.isEmpty(src)) {
            return null;
        }

        return new BigDecimal(src);
    }

    /**
     * {@link BigDecimal}の変換する。<br>
     * @param src 変換元
     * @return 変換後の値
     */
    public static BigDecimal convertToBigDecimal(Double src) {
        if (src == null) {
            return null;
        }

        return new BigDecimal(src);
    }

    /**
     * {@link Double}に変換する。<br>
     * @param src 対象
     * @return 変換後の文字列
     */
    public static Double convertToDouble(BigDecimal src) {
        if (src == null) {
            return null;
        }

        return src.doubleValue();
    }
}
