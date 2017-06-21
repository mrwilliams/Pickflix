package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.Models.MovieInfo;
import mobi.kewi.pickflix.Networking.NetworkEndpoints;
import mobi.kewi.pickflix.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsFragment extends Fragment {

    final public String IMAGE_URL = "http://image.tmdb.org/t/p/w500/";
    final public String MOVIE_URL = "https://api.themoviedb.org/3/movie/";

    public static final String ARG_ITEM_ID = "index";

    public static MovieDetailsFragment newInstance(int index) {
            Bundle args = new Bundle();
            args.putInt(ARG_ITEM_ID, index);
            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(args);
            return fragment;
        }

        private View rootView;
        FragmentActivity activity;
        @BindView(R.id.movie_details_toolbar_layout)CollapsingToolbarLayout collapsingToolbarLayout;
        @BindView(R.id.movie_homepage)Button movie_homepage;
        @BindView(R.id.backdrop_image)ImageView backdrop_image;
        @BindView(R.id.movie_title) TextView movie_title;
        @BindView(R.id.movie_tagline)TextView movie_tagline;
        @BindView(R.id.movie_overview)TextView movie_overview;

    @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 final Bundle savedInstanceState) {

            activity = getActivity();
            Intent intent = activity.getIntent();
            ButterKnife.bind(activity);

        Integer movieId = intent.getIntExtra("movie_id", 0);
        getMovieInfo(movieId);

            if (rootView != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            } else {
                rootView = inflater.inflate(R.layout.activity_movie_details,
                        container, false);

                collapsingToolbarLayout = ButterKnife.findById(rootView, R.id.movie_details_toolbar_layout);
                collapsingToolbarLayout.setTitleEnabled(true);
                collapsingToolbarLayout.setTitle("Pickflix");
                collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppTheme);
                collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
                collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

            }
                return rootView;
        }

    private void getMovieInfo(Integer movieId) {
        String moviedetailsCall = movieId+"?language=en-US&api_key=ab5b7a43e943cb2cb68881139eda4012";
        Log.i("Retrofit", "URL: "+moviedetailsCall);
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkEndpoints request = retrofit.create(NetworkEndpoints.class);
        //Async call to Skiddle
        Call<MovieInfo> call = request.getMovieInfo(moviedetailsCall);
        call.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                final MovieInfo movieInfoResponse = response.body();
                if (movieInfoResponse != null) {

                    // Define views and set the elements of the details page
                    String movieTitle = movieInfoResponse.getTitle();
                    movie_title = ButterKnife.findById(rootView, R.id.movie_title);
                    movie_title.setText(movieTitle);

                    String movieOverview = movieInfoResponse.getOverview();
                    movie_overview = ButterKnife.findById(rootView, R.id.movie_overview);
                    movie_overview.setText(movieOverview);

                    String movieHomepage = movieInfoResponse.getHomepage();
                    movie_homepage = ButterKnife.findById(rootView, R.id.movie_homepage);
                    final Uri uri = Uri.parse(movieHomepage);
                    movie_homepage.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
                            if (webIntent.resolveActivity(activity.getPackageManager()) != null) {
                                startActivity(webIntent);
                            }
                        }
                    });

                    //Set backdrop image
                    String backdropImage = movieInfoResponse.getBackdropPath();
                    backdrop_image = ButterKnife.findById(rootView, R.id.backdrop_image);
                    String backdrop_url = (IMAGE_URL + backdropImage);
                    Picasso.with(getContext()).load(backdrop_url).into(backdrop_image);

                    String movieTagline = movieInfoResponse.getTagline();
                    movie_tagline = ButterKnife.findById(rootView, R.id.movie_tagline);
                    movie_tagline.setText(movieTagline);;

                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });
    }
}