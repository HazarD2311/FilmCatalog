package vsu.amm.filmcatalog.domain.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import vsu.amm.filmcatalog.domain.FavouriteFilm;
import vsu.amm.filmcatalog.utils.AsyncTransformer;

public class FavouriteFilmDAO extends BaseDaoImpl<FavouriteFilm, Integer> {

    public FavouriteFilmDAO(ConnectionSource connectionSource,
                            Class<FavouriteFilm> data) throws SQLException {
        super(connectionSource, data);
    }

    /**
     * выгружает из базы все избранные фильмы
     * @return список с избранными фильмами
     */
    public Observable<List<FavouriteFilm>> getAllFavouriteFilms() {
        return Observable.fromCallable(FavouriteFilmDAO.this::queryForAll)
                .compose(new AsyncTransformer<>());
    }

    /**
     * добавляет в базу избранный фильм
     * @param favouriteFilm
     * @throws SQLException
     */
    public void addFavouriteFilm(FavouriteFilm favouriteFilm) throws SQLException {
        FavouriteFilmDAO.this.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                FavouriteFilmDAO.this.create(favouriteFilm);
                return null;
            }
        });
    }

    /**
     * удаляет избранный фильм из базы
     * @param favouriteFilm
     * @throws SQLException
     */
    public void deleteFavouriteFilm(FavouriteFilm favouriteFilm) throws SQLException {
        FavouriteFilmDAO.this.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                FavouriteFilmDAO.this.delete(favouriteFilm);
                return null;
            }
        });
    }

    /**
     * поиск избранного фильма по film_id
     * id не собственный (сгенерированный orm), а пришедший с сервера
     * @param id
     * @return избранный фильм
     * @throws SQLException
     */
    public FavouriteFilm findFavouriteByFilmId(Long id) throws SQLException {
        return FavouriteFilmDAO.this
                .queryBuilder()
                .where()
                .eq(FavouriteFilm.FILM_ID, id)
                .queryForFirst();
    }
}
