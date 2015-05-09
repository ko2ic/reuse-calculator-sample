package ko2.ic.android.common.ormlite;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import ko2.ic.android.common.core.exception.DatabaseException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * SQLDao用の抽象クラス<br>
 * @param <E> エンティティの型
 * @param <T> IDカラムの型
 * @author $Author: ishii_ko $
 * @version $Revision: 63 $
 */
public abstract class AbstractOrmliteDao<E, T> {
    /** JDBCDao */
    private RuntimeExceptionDao<E, T> jdbcDao;

    /**
     * IDからエンティティを１件取得する。<br>
     * @param id ID
     * @return エンティティ
     */
    public E findById(T id) {
        return jdbcDao().queryForId(id);
    }

    /**
     * すべてのエンティティを取得する。<br>
     * @return エンティティのリスト
     */
    public List<E> findAll() {
        return jdbcDao().queryForAll();
    }

    /**
     * 指定のエンティティを１件登録する。<br>
     * @param entity エンティティ
     */
    public void insert(E entity) {
        jdbcDao().create(entity);
    }

    /**
     * 指定のエンティティを１件更新する。<br>
     * @param entity エンティティ
     */
    public void update(E entity) {
        jdbcDao().update(entity);
    }

    /**
     * 指定のエンティティを１件更新or登録する。<br>
     * @param entity エンティティ
     */
    public void upsert(E entity) {
        jdbcDao().createOrUpdate(entity);
    }

    /**
     * 指定のエンティティを１件削除する。<br>
     * @param entity エンティティ
     */
    public void delete(E entity) {
        jdbcDao().delete(entity);
    }

    /**
     * バッチ登録を行う。<br>
     * @param entityList エンティティリスト
     */
    public void insertBatch(final List<E> entityList) {
        jdbcDao().callBatchTasks(new Callable<Void>() {
            /**
             * {@inheritDoc}
             */
            public Void call() throws Exception {
                for (E entity : entityList) {
                    insert(entity);
                }

                return null;
            }
        });
    }

    /**
     * バッチ更新を行う。<br>
     * @param entityList エンティティリスト
     */
    public void updateBatch(final List<E> entityList) {
        jdbcDao().callBatchTasks(new Callable<Void>() {
            /**
             * {@inheritDoc}
             */
            public Void call() throws Exception {
                for (E entity : entityList) {
                    update(entity);
                }

                return null;
            }
        });
    }

    /**
     * バッチ更新or登録する。<br>
     * @param entityList エンティティリスト
     */
    public void upsertBatch(final List<E> entityList) {
        jdbcDao().callBatchTasks(new Callable<Void>() {
            /**
             * {@inheritDoc}
             */
            public Void call() throws Exception {
                for (E entity : entityList) {
                    upsert(entity);
                }

                return null;
            }
        });
    }

    /**
     * バッチ削除する。<br>
     * @param entityList エンティティリスト
     */
    public void deleteBatch(final List<E> entityList) {
        jdbcDao().callBatchTasks(new Callable<Void>() {
            /**
             * {@inheritDoc}
             */
            public Void call() throws Exception {
                for (E entity : entityList) {
                    delete(entity);
                }

                return null;
            }
        });
    }

    /**
     * JDBC接続を行うDaoを作成する。
     * @return jdbc接続Dao
     */
    protected synchronized RuntimeExceptionDao<E, T> jdbcDao() {
        try {
            if (jdbcDao != null) {
                return jdbcDao;
            }

            Dao<E, T> dao = getDatabaseHelper().getDao(getEntityClass());
            jdbcDao = new RuntimeExceptionDao<E, T>(dao);

            return jdbcDao;
        } catch (SQLException e) {
            throw new DatabaseException("Daoの生成に失敗しました。", e);
        }
    }

    /**
     * {@link AbstractOrmLiteDatabaseHelper}を取得する。<br>
     * @return {@link AbstractOrmLiteDatabaseHelper}を継承したインスタンス
     */
    protected abstract AbstractOrmLiteDatabaseHelper getDatabaseHelper();

    /**
     * エンティティのクラスを取得する。<br>
     * @return エンティティのクラス
     */
    protected abstract Class<E> getEntityClass();
}
