package vsu.amm.filmcatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vsu.amm.filmcatalog.adapter.FilmRecyclerAdapter;
import vsu.amm.filmcatalog.model.response.Film;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilmRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_films);
        recyclerAdapter = new FilmRecyclerAdapter(getTestFilms());
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
}
