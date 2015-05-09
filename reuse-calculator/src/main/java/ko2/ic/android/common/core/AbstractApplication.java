package ko2.ic.android.common.core;

import ko2.ic.android.common.core.utils.LogUtil;
import android.app.Application;

/**
 * {@link Application}を使用する際の抽象クラス。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 754 $
 */
public abstract class AbstractApplication extends Application {
    /** 環境設定区分 */
    protected EnvType envType = EnvType.NULL;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        this.envType = EnvType.toEnum(getApplicationEnvTypeDef());
        settingLogging();

        LogUtil.i(getClass(), "onCreate");
        super.onCreate();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTerminate() {
        LogUtil.i(getClass(), "onTerminate");
        super.onTerminate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        LogUtil.i(getClass(), "onLowMemory");
        super.onLowMemory();
    }

    /**
     * アプリケーションログタグを取得する。<br>
     * @return アプリケーションログタグ
     */
    protected abstract String getApplicationLogTag();

    /**
     * アプリケーション環境区分を取得する。<br>
     * @return アプリケーション環境区分
     */
    protected abstract String getApplicationEnvTypeDef();

    /**
     * ロギングの設定を行う。
     */
    private void settingLogging() {
        if (envType.isProduct()) {
            return;
        }
        LogUtil.init(getApplicationLogTag(), true);
    }

    /**
     * 環境区分。<br>
     * @author $Author: ishii_ko $
     * @version $Revision: 754 $
     */
    public static enum EnvType {
        /** null */
        NULL(null),

        /** 開発環境 */
        DEVELOPMENT("0"),

        /** ステージング環境 */
        STAGING("1"),

        /** 本番環境 */
        PRODUCT("2");

        /** コード値 */
        private final String code;

        /**
         * MopitaApi結果ステータス区分。<br>
         * @param code コード値
         */
        private EnvType(String code) {
            this.code = code;
        }

        /**
         * コード値に変換する。<br>
         * @return コード値
         */
        public String toCode() {
            return this.code;
        }

        /**
         * 区分値に変換する。<br>
         * @param status 区分値のコード
         * @return 区分値
         */
        public static EnvType toEnum(String status) {
            for (EnvType envType : EnvType.values()) {
                if (envType.isNull()) {
                    continue;
                }

                if (envType.toCode().equals(status)) {
                    return envType;
                }
            }

            return EnvType.NULL;
        }

        /**
         * {@inheritDoc}
         */
        public boolean isNull() {
            return this == NULL;
        }

        /**
         * 本番環境かどうか。<br>
         * @return 本番環境の場合true。
         */
        public boolean isProduct() {
            return this == PRODUCT;
        }
    }
}
