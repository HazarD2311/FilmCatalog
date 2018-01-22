package vsu.amm.filmcatalog.app.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vsu.amm.filmcatalog.utils.Const;
import vsu.amm.filmcatalog.app.api.FilmApi;

@Module
public class HttpModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public FilmApi provideApi(Retrofit retrofit) {
        return retrofit.create(FilmApi.class);
    }
}
