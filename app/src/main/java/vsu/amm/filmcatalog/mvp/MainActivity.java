package vsu.amm.filmcatalog.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vsu.amm.filmcatalog.App;
import vsu.amm.filmcatalog.Const;
import vsu.amm.filmcatalog.R;
import vsu.amm.filmcatalog.adapter.FilmRecyclerAdapter;
import vsu.amm.filmcatalog.model.Film;
import vsu.amm.filmcatalog.model.FilmResponse;
import vsu.amm.filmcatalog.mvp.interfaces.FilmView;

public class MainActivity extends AppCompatActivity implements FilmView {

    private Toolbar toolbar;
    private EditText searchEdit;
    private ImageView searchClearBtn;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private FilmRecyclerAdapter recyclerAdapter;

    private List<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initResources();
        initRecyclerView();

        getDiscoverFilms();
    }

    private void getDiscoverFilms() {
        Call<FilmResponse> filmResponse = App.getInstanseApi().getDiscoverFilms(
                Const.API_KEY,
                Const.LANGUAGE,
                "popularity.desc",
                "false",
                "false",
                1);

        filmResponse.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                if (response.isSuccessful()) {
                    films = response.body().getResults();
                    progressBar.setVisibility(View.GONE);
                    recyclerAdapter.update(films);
                }
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {

            }
        });

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initResources() {
        searchEdit = (EditText) findViewById(R.id.search_edit_text);
        searchClearBtn = (ImageView) findViewById(R.id.search_clear);
        progressBar = (ProgressBar) findViewById(R.id.main_progress_bar);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_films);
//        recyclerAdapter = new FilmRecyclerAdapter(getTestFilms());
        recyclerAdapter = new FilmRecyclerAdapter(films, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private List<Film> getTestFilms() {
        List<Film> films = new ArrayList<>();
        Film film = new Film();
        film.setTitle("Оно");
        film.setOverview("Когда в городке Дерри, штат Мэн, начинают пропадать дети, несколько ребят сталкиваются со своими величайшими страхами и вынуждены помериться силами со злобным клоуном Пеннивайзом, чьи проявления жестокости и список жертв уходят в глубь веков.");
        film.setReleaseDate("5 сентября 2017");
        films.add(film);

        Film film2 = new Film();
        film2.setTitle("Джуманджи: Зов Джунглей");
        film2.setOverview("Когда в городке Дерри, штат Мэн, начинают пропадать дети, несколько ребят сталкиваются со своими величайшими страхами и вынуждены помериться силами со злобным клоуном Пеннивайзом, чьи проявления жестокости и список жертв уходят в глубь веков.");
        film2.setReleaseDate("5 сентября 2017");
        films.add(film2);

        return films;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showResult(List<Film> films) {

    }

    @Override
    public void showSnack(String message) {

    }
}
