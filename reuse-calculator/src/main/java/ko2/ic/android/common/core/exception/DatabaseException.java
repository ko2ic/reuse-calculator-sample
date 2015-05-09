package ko2.ic.android.common.core.exception;

/**
 * データベースの例外。
 * @author $Author: ishii_ko $
 * @version $Revision: 63 $
 */
public class DatabaseException extends RuntimeException {
    /**
     * コンストラクタ<br>
     */
    public DatabaseException() {
        super();
    }

    /**
     * メッセージ付きで例外を投げる<br>
     * @param message メッセージ
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * 原因となった例外を指定してコンストラクトする.
     * @param cause 原因となった例外
     */
    public DatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * エラーメッセージと原因となった例外を指定してコンストラクトする.
     * @param message エラーメッセージ
     * @param cause 原因となった例外
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}