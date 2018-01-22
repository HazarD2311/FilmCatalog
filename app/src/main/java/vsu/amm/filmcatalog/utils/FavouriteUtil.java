package vsu.amm.filmcatalog.utils;

import java.util.List;
import java.util.Objects;

import vsu.amm.filmcatalog.domain.FavouriteFilm;
import vsu.amm.filmcatalog.domain.Film;

public class FavouriteUtil {

    private List<FavouriteFilm> favouriteFilms;
    private List<Film> films;

    public FavouriteUtil(List<FavouriteFilm> favouriteFilms, List<Film> films) {
        this.favouriteFilms = favouriteFilms;
        this.films = films;
    }

    public void mapFilms() {
        for (int i = 0; i < films.size(); i++) {
            if (findInFavourite(films.get(i).getId())) {
                films.get(i).setFavourite(true);
            }
        }
    }

    public boolean findInFavourite(Long id) {
        for (FavouriteFilm favouriteFilm : favouriteFilms) {
            if (Objects.equals(favouriteFilm.getFilmId(), id)) {
                return true;
            }
        }
        return false;
    }

    public List<Film> getFilms() {
        return films;
    }
}
