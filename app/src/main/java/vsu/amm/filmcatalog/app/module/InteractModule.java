package vsu.amm.filmcatalog.app.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vsu.amm.filmcatalog.interactor.FilmInteract;

@Module
public class InteractModule {

    @Provides
    @Singleton
    public FilmInteract provideFilmInteract() {
        return new FilmInteract();
    }
}
