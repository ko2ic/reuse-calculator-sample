package ko2.ic.android.common.core.utils;

import android.util.Log;

public final class LogUtil {

    /** ログ出力用のタグ */
    private static String loggerTag = "ko2-andoid-application";

    /** ログを出力するかどうか */
    private static boolean isWriteLog = true;

    /**
     * プライベートコンストラクタ<br>
     */
    private LogUtil() {
    }

    /**
     * 初期化を行う。<br>
     * @param newLoggerTag ロガーのタグ
     * @param newWriteLog ログを出力するかどうか
     */
    public static void init(String newLoggerTag, boolean newWriteLog) {
        loggerTag = newLoggerTag;
        isWriteLog = newWriteLog;
    }

    /**
     * ログを出力するかどうか。<br>
     * @return ログを出力する場合true。
     */
    public static boolean isWriteLog() {
        return isWriteLog;
    }

    /**
     * ログを出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     */
    public static void v(Class<?> clazz, String message) {
        if (!isWriteLog) {
            return;
        }
        Log.v(loggerTag, buildMessage(clazz, message));
    }

    /**
     * ログを出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     * @param t 例外
     */
    public static void v(Class<?> clazz, String message, Throwable t) {
        if (!isWriteLog) {
            return;
        }
        Log.v(loggerTag, buildMessage(clazz, message), t);
    }

    /**
     * ログをデバッグ出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     */
    public static void d(Class<?> clazz, String message) {
        if (!isWriteLog) {
            return;
        }
        Log.d(loggerTag, buildMessage(clazz, message));
    }

    /**
     * ログをデバッグ出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     * @param t 例外
     */
    public static void d(Class<?> clazz, String message, Throwable t) {
        if (!isWriteLog) {
            return;
        }
        Log.d(loggerTag, buildMessage(clazz, message), t);
    }

    /**
     * ログを情報出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     */
    public static void i(Class<?> clazz, String message) {
        if (!isWriteLog) {
            return;
        }
        Log.i(loggerTag, buildMessage(clazz, message));
    }

    /**
     * ログを情報出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     * @param t 例外
     */
    public static void i(Class<?> clazz, String message, Throwable t) {
        if (!isWriteLog) {
            return;
        }
        Log.i(loggerTag, buildMessage(clazz, message), t);
    }

    /**
     * ログを警告出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     */
    public static void w(Class<?> clazz, String message) {
        if (!isWriteLog) {
            return;
        }
        Log.w(loggerTag, buildMessage(clazz, message));
    }

    /**
     * ログを警告出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     * @param t 例外
     */
    public static void w(Class<?> clazz, String message, Throwable t) {
        if (!isWriteLog) {
            return;
        }
        Log.w(loggerTag, buildMessage(clazz, message), t);
    }

    /**
     * ログをエラー出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     */
    public static void e(Class<?> clazz, String message) {
        if (!isWriteLog) {
            return;
        }
        Log.e(loggerTag, buildMessage(clazz, message));
    }

    /**
     * ログをエラー出力する。<br>
     * @param clazz 対象クラス
     * @param message メッセージ
     * @param t 例外
     */
    public static void e(Class<?> clazz, String message, Throwable t) {
        if (!isWriteLog) {
            return;
        }
        Log.e(loggerTag, buildMessage(clazz, message), t);
    }

    /**
     * 表示するログメッセージを作成する。<br>
     * @param clazz 対象のクラス名
     * @param message メッセージ
     * @return ログメッセージ
     */
    private static String buildMessage(Class<?> clazz, String message) {
        return clazz.getSimpleName() + ":" + message;
    }
}
