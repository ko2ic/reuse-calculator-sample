package ko2.ic.android.reuse.calc.infrastructure.repository.ormlite.transaction;

import ko2.ic.android.common.ormlite.AbstractOrmLiteDatabaseHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.support.ConnectionSource;

/**
 * データベースヘルパー。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 651 $
 */
@Singleton
public class DatabaseHelper extends AbstractOrmLiteDatabaseHelper {
    /** コンテキスト */
    // private final Context context;

    /** データベース名 */
    private static final String DATABASE_NAME = "calc.db";

    /** データベースバージョン */
    private static final int DATABASE_VERSION = 1;

    /** データベース初期SQLファイルパス */
    // private static final String DATABASE_INIT_SQL_FILE_PATH = "sql/initData.sql";

    /** データベース初期SQLファイルエンコード */
    // private static final String DATABASE_INIT_SQL_FILE_ENCODING = "UTF-8";

    /**
     * コンストラクタ<br>
     * @param context コンテキスト
     */
    @Inject
    public DatabaseHelper(Provider<Context> context) {
        super(context.get(), DATABASE_NAME, DATABASE_VERSION);

        // this.context = context.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // try {
        // TableUtils.createTableIfNotExists(connectionSource, BodyComposition.class);
        // TableUtils.createTableIfNotExists(connectionSource, PedometerPerDay.class);
        //
        // executeSqlFile(database, DATABASE_INIT_SQL_FILE_PATH, DATABASE_INIT_SQL_FILE_ENCODING);
        // } catch (SQLException e) {
        // throw new DatabaseInitializationException("テーブルの作成に失敗しました。", e);
        // } catch (IOException e) {
        // throw new DatabaseInitializationException("テーブルの作成に失敗しました。", e);
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    /**
     * SQLファイルを読み込む。<br>
     * @param database データベース
     * @param sqlFilePath SQLファイルパス
     * @param fileEncoding ファイルのエンコード
     * @throws IOException IO例外。
     */
    // private void executeSqlFile(SQLiteDatabase database, String sqlFilePath, String fileEncoding) throws IOException {
    // AssetManager assetManager = null;
    //
    // InputStream sqlFileInputStream = null;
    // BufferedReader bufferedReader = null;
    //
    // database.beginTransaction();
    //
    // try {
    // assetManager = context.getResources().getAssets();
    // sqlFileInputStream = assetManager.open(sqlFilePath);
    // bufferedReader = new BufferedReader(new InputStreamReader(sqlFileInputStream, Charset.forName(fileEncoding)));
    //
    // String sql;
    // while ((sql = bufferedReader.readLine()) != null) {
    // if (StringUtil.isEmpty(sql)) {
    // continue;
    // }
    // database.execSQL(sql);
    // }
    //
    // database.setTransactionSuccessful();
    // } finally {
    // if (sqlFileInputStream != null) {
    // try {
    // sqlFileInputStream.close();
    // } catch (IOException e) {
    // LogUtil.w(getClass(), "SQLファイルのクローズ処理に失敗しました。", e);
    // }
    //
    // sqlFileInputStream = null;
    // }
    // if (bufferedReader != null) {
    // try {
    // bufferedReader.close();
    // } catch (IOException e) {
    // LogUtil.w(getClass(), "SQLファイルのクローズ処理に失敗しました。", e);
    // }
    //
    // bufferedReader = null;
    // }
    //
    // database.endTransaction();
    // }
    // }
}
