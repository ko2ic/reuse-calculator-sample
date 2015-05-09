package ko2.ic.android.common.ormlite.transaction;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import ko2.ic.android.common.ormlite.AbstractOrmLiteDatabaseHelper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.j256.ormlite.misc.TransactionManager;

/**
 * トランザクションエグゼキューター。<br>
 * @param <T> データベースヘルパーの型
 * @author $Author: ishii_ko $
 * @version $Revision: 818 $
 */
public abstract class TransactionExecutor<T extends AbstractOrmLiteDatabaseHelper> {
    /** データベースヘルパー */
    private final T databaseHelper;

    /**
     * コンストラクタ<br>
     * @param helper DBヘルパー
     */
    protected TransactionExecutor(T helper) {
        this.databaseHelper = helper;
    }

    /**
     * 実行クラス。<br>
     * @param <R> 実行時の返却値の型
     * @param callable コーラブルオブジェクト
     * @return 実行時の返却値
     */
    public <R> R execute(Callable<R> callable) {
        try {
            return TransactionManager.callInTransaction(databaseHelper.getConnectionSource(), callable);
        } catch (SQLException e) {
            throw new RuntimeException("トランザクションでの実行時にエラーが発生しました。", e);
        }
    }

    /**
     * 実行クラス。<br>
     * @param <R> 実行時の返却値の型
     * @param callable コーラブルオブジェクト
     * @param editor SharedPreferencesエディター
     * @return 実行時の返却値
     */
    public <R> R execute(Callable<R> callable, SharedPreferences.Editor... editors) {
        R result = execute(callable);
        for (Editor editor : editors) {
            if (!editor.commit()) {
                throw new RuntimeException("トランザクションでの実行時にエラーが発生しました。");
            }
        }
        return result;
    }
}
