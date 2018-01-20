package vsu.amm.filmcatalog.app.api;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import vsu.amm.filmcatalog.domain.FilmResponse;

public interface FilmApi {

    @GET("discover/movie")
    Observable<FilmResponse> getDiscoverFilms(@Query("api_key") String api_key,
                                              @Query("language") String language,
                                              @Query("sort_by") String sort_by,
                                              @Query("include_adult") String include_adult,
                                              @Query("include_video") String include_video,
                                              @Query("page") int page);
}
