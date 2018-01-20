package vsu.amm.filmcatalog.ui.film;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import vsu.amm.filmcatalog.domain.Film;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FilmView extends MvpView {

    void showResult(List<Film> films);

    void showProgress();

    void hideProgress();

    void showSnack(String message);

}