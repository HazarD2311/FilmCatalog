package vsu.amm.filmcatalog.ui.film;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import vsu.amm.filmcatalog.Const;
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
    @BindView(R.id.main_progress_bar_horizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.recycler_films)
    RecyclerView recyclerView;
    @BindView(R.id.not_found_container)
    LinearLayout notFoundContainer;
    @BindView(R.id.not_found_text_view)
    TextView tvNotFound;
    @BindView(R.id.error_container)
    LinearLayout errorMessageContainer;
    @BindView(R.id.fab_refresh)
    FloatingActionButton fabRefresh;
    @BindView(R.id.main_container)
    RelativeLayout relativeLayout;
    @BindView(R.id.swipe_refresh_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private FilmAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initRecyclerView();
        initSwipeRefresh();

        presenter.getDiscoverFilms();
        //recyclerAdapter.update(getTestFilms());
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hideError();
                showProgress();
                presenter.updateFilms(searchEdit.getText().toString().equals(""));
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_films);
        //recyclerAdapter = new FilmAdapter(getTestFilms());
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
        if (recyclerAdapter.isListEmpty())
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBarHorizontal.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        progressBarHorizontal.setVisibility(View.GONE);
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

    @Override
    public void showError() {
        if (recyclerAdapter.isListEmpty()) {
            fabRefresh.setVisibility(View.VISIBLE);
            errorMessageContainer.setVisibility(View.VISIBLE);
        } else {
            showSnack(Const.ERROR_MESSAGE);
        }
    }

    @Override
    public void hideError() {
        fabRefresh.setVisibility(View.GONE);
        errorMessageContainer.setVisibility(View.GONE);
    }

    @Override
    public void showNotFound(String filmNotFound) {
        //TODO сделать вставку не найденного фильма в TextView: tvNotFound (...$s...)
        notFoundContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNotFound() {
        notFoundContainer.setVisibility(View.GONE);
    }
}
