package vsu.amm.filmcatalog.app.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vsu.amm.filmcatalog.interactor.FilmRepository;

@Module
public class InteractModule {

    @Provides
    @Singleton
    public FilmRepository provideFilmInteract() {
        return new FilmRepository();
    }
}
