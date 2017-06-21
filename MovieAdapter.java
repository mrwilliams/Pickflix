package mobi.kewi.pickflix.Views;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mobi.kewi.pickflix.Models.MovieResult;
import mobi.kewi.pickflix.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    // Adapter used to create custom views with text and image for each list row


    final public String IMAGEURL = "http://image.tmdb.org/t/p/w300/";
    private String poster;
    public static  List<MovieResult> movies = new ArrayList<>();
    public Resources res;
    Context context;
    private Collection<MovieResult> mMovies;
    private ArrayList<String> keys;


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView movie_title;
        public TextView movie_overview;
        public ImageView movie_poster;

        public ViewHolder(View convertView) {
            super(convertView);

            movie_title = (TextView) convertView.findViewById(R.id.movie_title);
            movie_overview = (TextView) convertView.findViewById(R.id.movie_overview);
            movie_poster = (ImageView) convertView.findViewById(R.id.movie_poster);
        }
    }

    public MovieAdapter(ArrayList<MovieResult> movies) {
        this.movies = movies;
        mMovies = new ArrayList<>();
        mMovies.addAll(movies);
    }

    public int getCount() {
        return movies.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public Object getItems() {return mMovies;}

    public long getItemId(int position) {
        return position;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        context = holder.movie_poster.getContext();

        MovieResult movie = movies.get(position);
        holder.movie_title.setText(movie.getTitle());
        holder.movie_overview.setText(movie.getOverview());

        poster = movie.posterPath;
        Picasso.with(context)
                .load(IMAGEURL+poster)
                .into(holder.movie_poster);
    }
}