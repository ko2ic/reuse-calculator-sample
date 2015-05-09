package ko2.ic.android.reuse.calc.infrastructure.repository.ormlite.transaction;

import ko2.ic.android.common.ormlite.transaction.TransactionExecutor;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * {@link DatabaseHelper}内でトランザクション制御を行うクラス。<br>
 * @author $Author: ishii_ko $
 * @version $Revision: 90 $
 */
@Singleton
public class DatabaseTransactionExecutor extends TransactionExecutor<DatabaseHelper> {

    /** データベースヘルパー */
    protected final DatabaseHelper databaseHelper;

    /**
     * コンストラクタ<br>
     * @param helper
     */
    @Inject
    public DatabaseTransactionExecutor(DatabaseHelper helper) {
        super(helper);
        this.databaseHelper = helper;
    }

}
