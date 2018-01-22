package vsu.amm.filmcatalog.ui.film.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vsu.amm.filmcatalog.R;


public class FilmViewHolder extends RecyclerView.ViewHolder {

    TextView title, overview, releaseDate;
    ImageView poster, favourite;

    public FilmViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.recycler_film_title);
        overview = (TextView) itemView.findViewById(R.id.recycler_film_overview);
        releaseDate = (TextView) itemView.findViewById(R.id.recycler_film_release_date);
        poster = (ImageView) itemView.findViewById(R.id.recycler_film_poster);
        favourite = (ImageView) itemView.findViewById(R.id.recycler_film_favourite);
    }
}
