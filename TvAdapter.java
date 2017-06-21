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

import mobi.kewi.pickflix.Models.TvResult;
import mobi.kewi.pickflix.R;

/**
 * Created by Kevin on 10/06/17.
 */

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    // Adapter used to create custom views with text and image for each list row


    final public String IMAGEURL = "http://image.tmdb.org/t/p/w300/";
    private String poster;
    public static List<TvResult> tv_shows = new ArrayList<>();
    public Resources res;
    Context context;
    private Collection<TvResult> mTvShows;
    private ArrayList<String> keys;



    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView tv_show_title;
        public TextView tv_show_overview;
        public ImageView tv_show_poster;

        public ViewHolder(View convertView) {
            super(convertView);

            tv_show_title = (TextView) convertView.findViewById(R.id.tv_show_title);
            tv_show_overview = (TextView) convertView.findViewById(R.id.tv_show_overview);
            tv_show_poster = (ImageView) convertView.findViewById(R.id.tv_show_poster);
        }
    }

    public TvAdapter(ArrayList<TvResult> tv_shows) {
        this.tv_shows = tv_shows;
        mTvShows = new ArrayList<>();
        mTvShows.addAll(tv_shows);
    }

    public int getCount() {
        return tv_shows.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public Object getItems() {return mTvShows;}

    public long getItemId(int position) {
        return position;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }
    @Override
    public int getItemCount() {
        return tv_shows.size();
    }

    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tv_show, parent, false);

        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        context = holder.tv_show_poster.getContext();

        TvResult tv_show = tv_shows.get(position);
        holder.tv_show_title.setText(tv_show.getName());
        holder.tv_show_overview.setText(tv_show.getOverview());

        poster = tv_show.posterPath;
        Picasso.with(context)
                .load(IMAGEURL+poster)
                .into(holder.tv_show_poster);
    }
}