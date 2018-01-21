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
import vsu.amm.filmcatalog.Const;
import vsu.amm.filmcatalog.app.App;
import vsu.amm.filmcatalog.domain.Film;
import vsu.amm.filmcatalog.domain.FilmResponse;
import vsu.amm.filmcatalog.interactor.FilmInteract;

@InjectViewState
public class FilmPresenter extends MvpPresenter<FilmView> {

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
                        List<Film> films = filmResponse.getResults();
                        getViewState().showResult(films);
                    }
                });
    }

    public void updateFilms(boolean searchIsEmpty) {
        if (searchIsEmpty) {
            getViewState().showProgress();
            getDiscoverFilms();
        } else {
            //TODO обновление при введенном тексте в строку поиск
        }
    }
}
