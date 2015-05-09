package ko2.ic.android.common.ormlite;

import android.content.Context;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

/**
 * データベースヘルパー。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 63 $
 */
public abstract class AbstractOrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * コンストラクタ<br>
     * @param context コンテキスト
     * @param databaseName データベース名
     * @param databaseVersion データベースバージョン
     */
    protected AbstractOrmLiteDatabaseHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }
}
