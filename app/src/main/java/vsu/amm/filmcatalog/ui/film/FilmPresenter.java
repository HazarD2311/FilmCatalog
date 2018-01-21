package vsu.amm.filmcatalog.ui.film;

import android.content.Context;
import android.widget.TextView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vsu.amm.filmcatalog.app.App;
import vsu.amm.filmcatalog.domain.Film;
import vsu.amm.filmcatalog.domain.FilmResponse;
import vsu.amm.filmcatalog.interactor.FilmRepository;

@InjectViewState
public class FilmPresenter extends MvpPresenter<FilmView> {

    @Inject
    FilmRepository repository;
    @Inject
    Context context;

    //введенное название фильма в строку поиска
    private String searchFilm = "";

    public FilmPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getDiscoverFilms() {
        Observable<FilmResponse> responseObservable = repository.getDiscoverFilms();
        subscribe(responseObservable);
    }

    public void getFilmsByName(String filmName) {
        searchFilm = filmName;
        if (filmName.equals("")) {
            getDiscoverFilms();
        } else {
            Observable<FilmResponse> responseObservable = repository.getFilmsByName(filmName);
            subscribe(responseObservable);
        }
    }

    private void subscribe(Observable<FilmResponse> responseObservable) {
        responseObservable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FilmResponse>() {
                    @Override
                    public void onCompleted() {
                        getViewState().hideProgress();
                        getViewState().hideError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideProgress();
                        getViewState().showError();
                    }

                    @Override
                    public void onNext(FilmResponse filmResponse) {
                        if (filmResponse.getTotalResults() == 0) {
                            getViewState().showNotFound(searchFilm);
                        }
                        List<Film> films = filmResponse.getResults();
                        getViewState().showResult(films);
                    }
                });
    }

    public void textChangeListener(TextView textView) {
        repository.observableTextChange(textView)
                .subscribe(string -> {
                    getViewState().hideNotFound();
                    getViewState().showResultTextChange(string);
                }, error -> {
                    getViewState().showError();
                    getViewState().showSnack(error.getMessage());
                });
    }

    public void updateFilms(String searchText) {
        if (searchText.equals("")) {
            getViewState().showProgress();
            getDiscoverFilms();
        } else {
            getViewState().showProgress();
            getFilmsByName(searchText);
        }
    }
}
