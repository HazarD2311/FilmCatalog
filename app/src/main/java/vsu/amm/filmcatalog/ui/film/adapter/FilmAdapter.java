package vsu.amm.filmcatalog.ui.film.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vsu.amm.filmcatalog.utils.Const;
import vsu.amm.filmcatalog.R;
import vsu.amm.filmcatalog.domain.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private List<Film> films;
    private Context context;
    private OnClickFilmListener clickListener;

    public FilmAdapter(Context context,
                       FilmAdapter.OnClickFilmListener clickListener) {
        this.films = new ArrayList<>();
        this.context = context;
        this.clickListener = clickListener;
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
        if (film.isFavourite()) {
            holder.favourite.setImageDrawable(context.getDrawable(R.drawable.ic_heart_fill));
        } else {
            holder.favourite.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
        }
        Glide
                .with(context)
                .load(Const.IMAGE_BASE_URL + film.getPosterPath())
                .into(holder.poster);

        if (clickListener != null) {
            holder.itemView.setOnClickListener(v -> clickListener.onClickFilm(film, position));
            holder.favourite.setOnClickListener(v -> clickListener.onFavouriteClick(film, position));
        }
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

    public void changeFavouriteImage(int position) {
        films.get(position).reverseFavourite();
        notifyItemChanged(position);
    }

    public Boolean isListEmpty() {
        return films.isEmpty();
    }

    public interface OnClickFilmListener {
        void onClickFilm(Film film, int position);

        void onFavouriteClick(Film film, int position);
    }
}
