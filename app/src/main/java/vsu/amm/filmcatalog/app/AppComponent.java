package vsu.amm.filmcatalog.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import vsu.amm.filmcatalog.app.module.ContextModule;
import vsu.amm.filmcatalog.app.module.HttpModule;
import vsu.amm.filmcatalog.app.module.InteractModule;
import vsu.amm.filmcatalog.interactor.FilmInteract;
import vsu.amm.filmcatalog.ui.film.FilmActivity;
import vsu.amm.filmcatalog.ui.film.FilmPresenter;

@Singleton
@Component(modules = {
        InteractModule.class,
        ContextModule.class,
        HttpModule.class
})
public interface AppComponent {

    Context getContext();

    void inject(FilmInteract filmInteract);

    void inject(FilmActivity filmActivity);

    void inject(FilmPresenter filmPresenter);
}
