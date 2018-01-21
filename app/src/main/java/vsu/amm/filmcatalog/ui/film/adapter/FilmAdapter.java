package vsu.amm.filmcatalog.ui.film.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vsu.amm.filmcatalog.Const;
import vsu.amm.filmcatalog.R;
import vsu.amm.filmcatalog.domain.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private List<Film> films;
    private Context context;

    public FilmAdapter(Context context) {
        this.films = new ArrayList<>();
        this.context = context;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_film, parent, false);
        return new FilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = films.get(position);
        holder.title.setText(film.getTitle());
        holder.overview.setText(film.getOverview());
        holder.releaseDate.setText(film.getReleaseDate());
        Glide
                .with(context)
                .load(Const.IMAGE_BASE_URL + film.getPosterPath())
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        if (films == null)
            return 0;
        else
            return films.size();
    }

    public void update(List<Film> filmList) {
        films = filmList;
        this.notifyDataSetChanged();
    }

    public Boolean isListEmpty() {
        return films.isEmpty();
    }

}
