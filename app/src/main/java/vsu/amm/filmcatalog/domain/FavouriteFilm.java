package vsu.amm.filmcatalog.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import vsu.amm.filmcatalog.domain.db.dao.FavouriteFilmDAO;

@DatabaseTable(tableName = FavouriteFilm.TABLE_NAME, daoClass = FavouriteFilmDAO.class)
public class FavouriteFilm implements Serializable {

    public static final String TABLE_NAME = "favourite_table";
    public static final String ID = "id";
    public static final String FILM_ID = "film_id";
    public static final String FILM_TITLE = "title";

    //id для таблицы
    @DatabaseField(columnName = ID, generatedId = true)
    private int id;

    //id фильма пришедшее с сервера
    @DatabaseField(columnName = FILM_ID)
    private Long filmId;

    //название фильма
    @DatabaseField(columnName = FILM_TITLE)
    private String title;

    public FavouriteFilm() {
    }

    public FavouriteFilm(Long filmId, String title) {
        this.filmId = filmId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
