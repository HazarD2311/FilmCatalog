package vsu.amm.filmcatalog.interactor;


import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import vsu.amm.filmcatalog.Const;
import vsu.amm.filmcatalog.app.App;
import vsu.amm.filmcatalog.app.api.FilmApi;
import vsu.amm.filmcatalog.domain.Film;
import vsu.amm.filmcatalog.domain.FilmResponse;

public class FilmInteract {

    @Inject
    FilmApi api;
    @Inject
    Context context;

    public FilmInteract() {
        App.getAppComponent().inject(this);
    }

    public Observable<FilmResponse> getDiscoverFilms() {
        return api.getDiscoverFilms(
                Const.API_KEY,
                Const.LANGUAGE,
                "popularity.desc",
                "false",
                "false",
                1
        );
    }
}
