package ko2.ic.android.common.core.exception;

/**
 * データベース初期化例外。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 63 $
 */
public class DatabaseInitializationException extends RuntimeException {
    /**
     * コンストラクタ<br>
     */
    public DatabaseInitializationException() {
        super();
    }

    /**
     * メッセージ付きで例外を投げる<br>
     * @param message メッセージ
     */
    public DatabaseInitializationException(String message) {
        super(message);
    }

    /**
     * 原因となった例外を指定してコンストラクトする.
     * @param cause 原因となった例外
     */
    public DatabaseInitializationException(Throwable cause) {
        super(cause);
    }

    /**
     * エラーメッセージと原因となった例外を指定してコンストラクトする.
     * @param message エラーメッセージ
     * @param cause 原因となった例外
     */
    public DatabaseInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
