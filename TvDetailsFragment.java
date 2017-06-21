package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.Models.TvShowInfo;
import mobi.kewi.pickflix.Networking.NetworkEndpoints;
import mobi.kewi.pickflix.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 10/06/17.
 */

public class TvDetailsFragment extends Fragment {

    final public String IMAGE_URL = "http://image.tmdb.org/t/p/w500/";
    final public String TV_URL = "https://api.themoviedb.org/3/tv/";

    public static final String ARG_ITEM_ID = "index";

    public static TvDetailsFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, index);
        TvDetailsFragment fragment = new TvDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View rootView;
    FragmentActivity activity;

    @BindView(R.id.tv_details_toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_homepage)Button tv_homepage;
    @BindView(R.id.backdrop_image)ImageView backdrop_image;
    @BindView(R.id.tv_title)TextView tv_title;
    @BindView(R.id.tv_type)TextView tv_type;
    @BindView(R.id.tv_overview)TextView tv_overview;
    @BindView(R.id.tv_country)TextView tv_country;
    @BindView(R.id.tv_air_date)TextView tv_air_date;
    @BindView(R.id.tv_seasons)TextView tv_seasons;
    @BindView(R.id.tv_episodes)TextView tv_episodes;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        activity = getActivity();
        Intent intent = activity.getIntent();
        ButterKnife.bind(activity);

        Integer tvShowId = intent.getIntExtra("tv_show_id", 0);
        getTvShowInfo(tvShowId);

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.activity_tv_details,
                    container, false);

            collapsingToolbarLayout = ButterKnife.findById(rootView, R.id.tv_details_toolbar_layout);
            collapsingToolbarLayout.setTitleEnabled(true);
            collapsingToolbarLayout.setTitle("Pickflix");
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppTheme);
            collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));


            // Define views and set the elements of the details page
            String tvTitle = intent.getStringExtra("tv_show_name");
            tv_title = ButterKnife.findById(rootView, R.id.tv_title);
            tv_title.setText(tvTitle);

            String tvOverview = intent.getStringExtra("tv_overview");
            tv_overview = ButterKnife.findById(rootView, R.id.tv_overview);
            tv_overview.setText(tvOverview);

            String tvShowAirDate = intent.getStringExtra("tv_air_date");
            tv_air_date = ButterKnife.findById(rootView, R.id.tv_air_date);
            tv_air_date.setText(tvShowAirDate);

            //Set backdrop image
            String backdropImage = intent.getStringExtra("tv_backdrop");
            backdrop_image = ButterKnife.findById(rootView, R.id.backdrop_image);
            String backdrop_url = (IMAGE_URL + backdropImage);
            Picasso.with(getContext()).load(backdrop_url).into(backdrop_image);
        }
        return rootView;
    }
   private void getTvShowInfo(Integer tvShowId) {
        String tvdetailsCall = tvShowId + "?api_key=ab5b7a43e943cb2cb68881139eda4012";
        Log.i("Retrofit", "URL: " + tvdetailsCall);
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TV_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkEndpoints request = retrofit.create(NetworkEndpoints.class);
        //Async call to TMDB
        Call<TvShowInfo> call = request.getTvInfo(tvdetailsCall);
        call.enqueue(new Callback<TvShowInfo>() {
            @Override
            public void onResponse(Call<TvShowInfo> call, Response<TvShowInfo> response) {
                final TvShowInfo tvInfoResponse = response.body();
                if (tvInfoResponse != null) {

                    String tvType = tvInfoResponse.getType();
                    tv_type = ButterKnife.findById(rootView, R.id.tv_type);
                    tv_type.setText(tvType);

                    String tvSeasons = tvInfoResponse.getNumberOfSeasons().toString();
                    String tvSeasonsText = (tvSeasons+" Seasons");
                    tv_seasons = ButterKnife.findById(rootView, R.id.tv_seasons);
                    tv_seasons.setText(tvSeasonsText);


                    String tvEpisodes = tvInfoResponse.getNumberOfEpisodes().toString();
                    String tvEpisodeText = (tvEpisodes+" Episodes");
                    tv_episodes = ButterKnife.findById(rootView, R.id.tv_episodes);
                    tv_episodes.setText(tvEpisodeText);

                    List tvShowCountry = tvInfoResponse.getOriginCountry();
                    String tvCountry  = TextUtils.join(", ", tvShowCountry);
                    tv_country = ButterKnife.findById(rootView, R.id.tv_country);
                    tv_country.setText(tvCountry);

                    String tvHomepage =  tvInfoResponse.getHomepage();
                    tv_homepage = ButterKnife.findById(rootView, R.id.tv_homepage);
                    final Uri uri = Uri.parse(tvHomepage);
                    tv_homepage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
                    if (webIntent.resolveActivity(activity.getPackageManager()) != null) {
                        startActivity(webIntent);
                    }
                }
            });

                }
            }

            @Override
            public void onFailure(Call<TvShowInfo> call, Throwable t) {

            }
        });

        }


}