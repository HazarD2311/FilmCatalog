package vsu.amm.filmcatalog.ui.film;

import android.content.Context;

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
import vsu.amm.filmcatalog.interactor.FilmInteract;

@InjectViewState
public class FilmPresenter extends MvpPresenter<FilmView> implements Observer<FilmResponse> {

    @Inject
    FilmInteract interact;
    @Inject
    Context context;

    public FilmPresenter() {
        App.getAppComponent().inject(this);
    }

    public void getDiscoverFilms() {
        Observable<FilmResponse> responseObservable = interact.getDiscoverFilms();
        responseObservable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(FilmResponse filmResponse) {
        List<Film> films = filmResponse.getResults();
        getViewState().hideProgress();
        getViewState().showResult(films);
    }
}
