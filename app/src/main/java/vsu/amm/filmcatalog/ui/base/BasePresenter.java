package vsu.amm.filmcatalog.ui.base;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V view;

    protected V getView() {
        return view;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
