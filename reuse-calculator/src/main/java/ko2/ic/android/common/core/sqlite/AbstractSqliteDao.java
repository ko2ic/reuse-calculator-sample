package ko2.ic.android.common.core.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * SQLDao用の抽象クラス<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 63 $
 */
public abstract class AbstractSqliteDao {
    /** データベースヘルパー */
    protected AbstractDatabaseHelper databaseHelper;

    /**
     * コンストラクタ<br>
     * @param context コンテキスト
     * @param factory ファクトリ
     */
    protected AbstractSqliteDao(Context context, CursorFactory factory) {
        databaseHelper = getDatabaseHelper();
    }

    /**
     * 読み込み可能なデータベースを取得する。<br>
     * @return データベース
     */
    protected SQLiteDatabase getReadableDatabase() {
        databaseHelper.lock();

        return databaseHelper.getReadableDatabase();
    }

    /**
     * 書き込み可能なデータベースを取得する。<br>
     * @return データベース
     */
    protected SQLiteDatabase getWritableDatabase() {
        databaseHelper.lock();

        return databaseHelper.getWritableDatabase();
    }

    /**
     * データベースをクローズする。<br>
     * @param cursor カーソル
     * @param database データベース
     */
    protected void closeDatabase(Cursor cursor, SQLiteDatabase database) {
        try {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
            if (database != null) {
                database.close();
                database = null;
            }
        } finally {
            databaseHelper.unLock();
        }
    }

    /**
     * {@link AbstractDatabaseHelper}を取得する。<br>
     * @return {@link AbstractDatabaseHelper}を継承したインスタンス
     */
    protected abstract AbstractDatabaseHelper getDatabaseHelper();
}
