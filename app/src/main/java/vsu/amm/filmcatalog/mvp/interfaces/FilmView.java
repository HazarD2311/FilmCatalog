package vsu.amm.filmcatalog.mvp.interfaces;

import java.util.List;

import vsu.amm.filmcatalog.model.Film;

public interface FilmView {

    void showProgress();
    void hideProgress();
    void showResult(List<Film> films);
    void showSnack(String message);

}
