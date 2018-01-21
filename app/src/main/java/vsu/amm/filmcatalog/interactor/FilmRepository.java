package vsu.amm.filmcatalog.interactor;


import android.content.Context;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import vsu.amm.filmcatalog.Const;
import vsu.amm.filmcatalog.app.App;
import vsu.amm.filmcatalog.app.api.FilmApi;
import vsu.amm.filmcatalog.domain.FilmResponse;

public class FilmRepository {

    @Inject
    FilmApi api;
    @Inject
    Context context;

    public FilmRepository() {
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

    public Observable<FilmResponse> getFilmsByName(String filmName) {
        return api.getFilmsFilteredByName(
                Const.API_KEY,
                Const.LANGUAGE,
                filmName,
                "false",
                1
        );
    }

    public Observable<String> observableTextChange(TextView searchTextView) {
        return RxTextView.textChanges(searchTextView).skip(1) // Пропускаем первый вызов
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(300, TimeUnit.MILLISECONDS) // Задержка
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<CharSequence, Observable<String>>() {
                    @Override
                    public Observable<String> call(CharSequence charSequence) {
                        return Observable.just(charSequence.toString());
                    }
                });
    }

}
