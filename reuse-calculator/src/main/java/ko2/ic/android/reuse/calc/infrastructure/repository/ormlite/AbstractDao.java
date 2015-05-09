package ko2.ic.android.reuse.calc.infrastructure.repository.ormlite;

import ko2.ic.android.common.ormlite.AbstractOrmliteDao;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * SQLDao用の抽象クラス<br>
 * @param <E> エンティティの型
 * @param <T> IDカラムの型
 * @author $Author: ishii_ko $
 * @version $Revision: 347 $
 */
@Singleton
public abstract class AbstractDao<E, T> extends AbstractOrmliteDao<E, T> {

    /** コンテキスト */
    private final Context context;

    /**
     * コンストラクタ<br>
     * @param context コンテキスト
     */
    public AbstractDao(Provider<Context> context) {
        this.context = context.get();
    }

    /**
     * 共通設定情報を取得する。<br>
     * @return 共通設定
     */
    protected SharedPreferences sharedPreferences(String sharedPreferenceName, int mode) {
        return context.getSharedPreferences(sharedPreferenceName, mode);
    }

    /**
     * 共通設定情報エディタを取得する。<br>
     * @return 共通設定エディタ
     */
    protected Editor sharedPreferencesEditor(String sharedPreferenceName, int mode) {
        SharedPreferences sharedPreferences = sharedPreferences(sharedPreferenceName, mode);
        return sharedPreferences.edit();
    }
}
