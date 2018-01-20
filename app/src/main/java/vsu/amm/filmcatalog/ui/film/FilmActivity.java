package vsu.amm.filmcatalog.ui.film;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import vsu.amm.filmcatalog.R;
import vsu.amm.filmcatalog.ui.film.adapter.FilmAdapter;
import vsu.amm.filmcatalog.domain.Film;

public class FilmActivity extends MvpAppCompatActivity implements FilmView {

    @InjectPresenter
    FilmPresenter presenter;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_edit_text)
    EditText searchEdit;
    @BindView(R.id.search_clear)
    ImageView searchClearBtn;
    @BindView(R.id.main_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_films)
    RecyclerView recyclerView;
    @BindView(R.id.main_container)
    RelativeLayout relativeLayout;

    private FilmAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initRecyclerView();

        presenter.getDiscoverFilms();
        //recyclerAdapter.update(getTestFilms());
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_films);
//        recyclerAdapter = new FilmAdapter(getTestFilms());
        recyclerAdapter = new FilmAdapter(this);
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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResult(List<Film> films) {
        recyclerAdapter.update(films);
    }

    @Override
    public void showSnack(String message) {
        Snackbar.make(
                relativeLayout,
                message,
                Snackbar.LENGTH_LONG)
                .show();
    }
}
