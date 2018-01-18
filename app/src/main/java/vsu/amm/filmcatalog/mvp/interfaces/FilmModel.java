package vsu.amm.filmcatalog.mvp.interfaces;


import java.util.List;

import vsu.amm.filmcatalog.model.Film;

public interface FilmModel {

    List<Film> getDiscoverFilm();
    List<Film> getSearchFilm(String name);
}
