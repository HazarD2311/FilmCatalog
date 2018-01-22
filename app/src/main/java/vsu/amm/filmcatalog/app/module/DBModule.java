package vsu.amm.filmcatalog.app.module;

import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vsu.amm.filmcatalog.domain.FavouriteFilm;
import vsu.amm.filmcatalog.domain.db.DBHelper;
import vsu.amm.filmcatalog.domain.db.dao.FavouriteFilmDAO;

@Module
public class DBModule {

    @Provides
    @Singleton
    public DBHelper provideDBHelper(Context context) {
        return new DBHelper(context);
    }

    @Provides
    @Singleton
    ConnectionSource provideConnectionSource(DBHelper dbHelper) {
        return dbHelper.getConnectionSource();
    }


    @Provides
    @Singleton
    public FavouriteFilmDAO provideWeatherDAO(ConnectionSource connectionSource) {
        FavouriteFilmDAO weatherDAO = null;
        try {
            weatherDAO = new FavouriteFilmDAO(connectionSource, FavouriteFilm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherDAO;
    }
}
