package ko2.ic.android.common.roboguice.activity;

import ko2.ic.android.common.core.utils.LogUtil;
import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.os.Bundle;

/**
 * {@link RoboActivity}を使用する際の抽象クラス。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 870 $
 */
public abstract class AbstractRoboActivity extends RoboActivity {

    /** アプリケーションDao */
    // @Inject
    // private InitConfigRepository configRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(getClass(), "onCreate");
        super.onCreate(savedInstanceState);

        dumpExtraParameters();

        // InitConfig config = configRepository.find();
        // if (config.isAgreeCollectedData()) {
        // GoogleAnalyticsLogTracker.getInstance().startSession();
        // }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onRestart() {
        LogUtil.i(getClass(), "onRestart");
        super.onRestart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onNewIntent(Intent intent) {
        LogUtil.i(getClass(), "onNewIntent");
        super.onNewIntent(intent);

        dumpExtraParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onResume() {
        LogUtil.i(getClass(), "onResume");
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        LogUtil.i(getClass(), "onStart");
        super.onStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPause() {
        LogUtil.i(getClass(), "onPause");
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        LogUtil.i(getClass(), "onStop");
        super.onStop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        LogUtil.i(getClass(), "onDestroy");
        super.onDestroy();

        // InitConfig config = configRepository.find();
        // if (config.isAgreeCollectedData()) {
        // GoogleAnalyticsTracker.getInstance().dispatch();
        // GoogleAnalyticsLogTracker.getInstance().stopSession();
        // }
    }

    /**
     * 追加パラメータをダンプする。<br>
     */
    private void dumpExtraParameters() {
        if (!LogUtil.isWriteLog()) {
            return;
        }

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        LogUtil.d(getClass(), "---------------------extractParameters dump-----------------------");
        for (String extraKey : extras.keySet()) {
            LogUtil.d(getClass(), String.format("%s=%s", extraKey, extras.get(extraKey)));
        }
        LogUtil.d(getClass(), "---------------------extractParameters dump-----------------------");
    }
}
