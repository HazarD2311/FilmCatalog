package vsu.amm.filmcatalog;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vsu.amm.filmcatalog.api.FilmApi;

public class App extends Application {

    private static Retrofit retrofit;
    private static FilmApi filmApi;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static FilmApi getInstanseApi() {
        if (filmApi == null) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Const.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            filmApi = retrofit.create(FilmApi.class);
        }
        return filmApi;
    }
}
