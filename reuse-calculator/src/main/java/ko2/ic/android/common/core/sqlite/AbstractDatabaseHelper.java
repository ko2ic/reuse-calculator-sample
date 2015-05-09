package ko2.ic.android.common.core.sqlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import ko2.ic.android.common.core.exception.DatabaseInitializationException;
import ko2.ic.android.common.core.utils.LogUtil;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * データベースヘルパー。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 941 $
 */
public abstract class AbstractDatabaseHelper extends SQLiteOpenHelper {
    /** ロックオブジェクト */
    private static final ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock();

    /** コンテキスト */
    private final Context context;

    /**
     * コンストラクタ。<br>
     * @param context コンテキスト
     * @param databaseName データベース名
     * @param databaseVersion データベースバージョン
     * @param factory カーソルファクトリ
     */
    public AbstractDatabaseHelper(Context context, String databaseName, int databaseVersion, CursorFactory factory) {
        super(context, databaseName, factory, databaseVersion);

        LogUtil.i(getClass(), "onCreate");
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            LogUtil.d(getClass(), "データベースを初期化しています。");
            executeSqlFile(database, getCreateTableSqlFilePath());
            LogUtil.d(getClass(), "テーブル作成が完了しました。");

            LogUtil.d(getClass(), "テーブルデータを初期化しています。");
            executeSqlFile(database, getInitDataSqlFilePath());
            LogUtil.d(getClass(), "テーブルデータの初期化が完了しました。");

        } catch (IOException e) {
            throw new DatabaseInitializationException("データベースの初期化中に例外が発生しました。", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
        try {
            LogUtil.d(getClass(), "テーブルデータを削除しています。");
            executeSqlFile(database, getDropTableSqlFilePath());
            LogUtil.d(getClass(), "テーブルデータの削除が完了しました。");
        } catch (IOException e) {
            throw new DatabaseInitializationException("データベースのアップグレード中に例外が発生しました。", e);
        }

        onCreate(database);
    }

    /**
     * ロックを取得する。<br>
     */
    public void lock() {
        REENTRANT_READ_WRITE_LOCK.writeLock().lock();
    }

    /**
     * ロックを解放する。<br>
     */
    public void unLock() {
        REENTRANT_READ_WRITE_LOCK.writeLock().unlock();
    }

    /**
     * データベース名を取得する。<br>
     * TODO オーバーライドしているが大丈夫？
     * @return データベース名
     */
    @Override
    public abstract String getDatabaseName();

    /**
     * データベースバージョンを取得する。<br>
     * @return データベースバージョン
     */
    protected abstract int getDatabaseVersion();

    /**
     * テーブル作成SQLファイルパスを取得する。<br>
     * @return テーブル作成SQLファイルパス
     */
    protected abstract String getCreateTableSqlFilePath();

    /**
     * テーブル初期データ作成SQLファイルパスを取得する。<br>
     * @return テーブル初期データ作成SQLファイルパス
     */
    protected abstract String getInitDataSqlFilePath();

    /**
     * テーブル削除SQLファイルパスを取得する。<br>
     * @return テーブル削除SQLファイルパス
     */
    protected abstract String getDropTableSqlFilePath();

    /**
     * SQLファイルのエンコードを取得する。<br>
     * @return SQLファイルのエンコード
     */
    protected abstract String getSqlFileEncoding();

    /**
     * SQLファイルを読み込む。<br>
     * @param database データベース
     * @param sqlFilePath SQLファイルパス
     * @throws IOException IO例外。
     */
    private void executeSqlFile(SQLiteDatabase database, String sqlFilePath) throws IOException {
        AssetManager assetManager = context.getResources().getAssets();

        InputStream sqlFileInputStream = null;
        BufferedReader bufferedReader = null;

        try {
            sqlFileInputStream = assetManager.open(sqlFilePath);
            bufferedReader = new BufferedReader(new InputStreamReader(sqlFileInputStream, Charset.forName(getSqlFileEncoding())));

            String sql;
            while ((sql = bufferedReader.readLine()) != null) {
                if ("".equals(sql)) {
                    continue;
                }
                database.execSQL(sql);
            }
        } finally {
            if (sqlFileInputStream != null) {
                sqlFileInputStream.close();
                sqlFileInputStream = null;
            }
            if (bufferedReader != null) {
                bufferedReader.close();
                bufferedReader = null;
            }
        }
    }
}
