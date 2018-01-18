package vsu.amm.filmcatalog.mvp;

import java.util.List;

import vsu.amm.filmcatalog.model.Film;
import vsu.amm.filmcatalog.mvp.interfaces.FilmModel;

public class FilmModelImpl implements FilmModel {

    @Override
    public List<Film> getDiscoverFilm() {
        return null;
    }

    @Override
    public List<Film> getSearchFilm(String name) {
        return null;
    }
}
