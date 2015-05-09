package ko2.ic.android.reuse.calc.infrastructure;

import roboguice.RoboGuice;
import android.app.Application;

/**
 * サービスロケータ。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 48083 $
 */
public final class ServiceLocator {
    /** アプリケーション */
    private static Application application;

    /**
     * コンストラクタ<br>
     */
    private ServiceLocator() {
    }

    /**
     * 初期化を行う。<br>
     * @param newApplication アプリケーション
     */
    public static void init(Application newApplication) {
        application = newApplication;
    }

    /**
     * コンポーネントを検索する。<br>
     * @param clazz クラス
     * @param <T> コンポーネントの型
     * @return コンポーネントのインスタンス
     */
    public static <T> T findComponents(Class<T> clazz) {
        return RoboGuice.getBaseApplicationInjector(application).getInstance(clazz);
    }
}
