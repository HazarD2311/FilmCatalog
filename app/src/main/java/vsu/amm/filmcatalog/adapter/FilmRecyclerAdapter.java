package vsu.amm.filmcatalog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vsu.amm.filmcatalog.R;
import vsu.amm.filmcatalog.model.response.Film;

public class FilmRecyclerAdapter extends RecyclerView.Adapter<FilmRecyclerAdapter.FilmViewHolder> {

    private List<Film> films;

    public FilmRecyclerAdapter(List<Film> films) {
        this.films = films;
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
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview, releaseDate;

        public FilmViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.recycler_film_title);
            overview = (TextView) itemView.findViewById(R.id.recycler_film_overview);
            releaseDate = (TextView) itemView.findViewById(R.id.recycler_film_release_date);
        }
    }

}
