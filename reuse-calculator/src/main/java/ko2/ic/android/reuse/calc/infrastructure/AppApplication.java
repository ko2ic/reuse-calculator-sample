package ko2.ic.android.reuse.calc.infrastructure;

import ko2.ic.android.common.core.AbstractApplication;

/**
 * アプリケーション。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 47706 $
 */
public class AppApplication extends AbstractApplication {

    /** アプリケーションログのタグ名 */
    private static final String APPLICATION_LOG_TAG = "reuse-calculator";

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        ServiceLocator.init(this);

        initSettingsHolder();
        configureLogTracking();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationEnvTypeDef() {
        // return getString(R.string.application_env_type);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getApplicationLogTag() {
        return APPLICATION_LOG_TAG;
    }

    /**
     * 設定情報ホルダーを初期化する。<br>
     */
    private void initSettingsHolder() {
        // HttpConnectionSettingsHolder.init(getResources().getInteger(R.integer.http_connection_timeout), getResources().getInteger(R.integer.http_socket_connection_timeout), findUserAgentString());
    }

    /**
     * ログ追跡の設定を行う。<br>
     */
    private void configureLogTracking() {
        // GoogleAnalyticsLogTracker.init(getString(R.string.google_analytics_web_property_id), getResources().getInteger(R.integer.google_analytics_dispatch_interval_minute), this,
        // envType.isProduct());
    }

    /**
     * ユーザエージェントをバインドする。<br>
     * @return ユーザエージェント文字列
     */
    // @TargetApi(3)
    // private String findUserAgentString() {
    // WebView webView = null;
    // try {
    // webView = new WebView(this);
    // WebSettings webSettings = webView.getSettings();
    //
    // return webSettings.getUserAgentString();
    // } finally {
    // if (webView != null) {
    // webView.destroy();
    // webView = null;
    // }
    // }
    // }
}
