package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.ItemClickSupport;
import mobi.kewi.pickflix.Models.MovieResult;
import mobi.kewi.pickflix.Models.Movies;
import mobi.kewi.pickflix.Networking.NetworkEndpoints;
import mobi.kewi.pickflix.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 09/06/17.
 */

public class MovieListFragment extends Fragment {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    final public int LIMIT = 100;
    final public String MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    final public int CACHETIME = 432000; // 5days
    private static final String ARG_PAGE = "ARG_PAGE";
    private LinearLayoutManager mLayoutManager;
    private FragmentActivity activity;
    private ArrayList<MovieResult> mAdapterItems;
    private ArrayList<String> mAdapterKeys;
    private MovieAdapter mMovieAdapter;
    private View rootView;
    private MovieResult movie;

    public static MovieListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.progress) ProgressBar mProgressBar;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.movie_recycler) RecyclerView mRecyclerView;

    @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
        final Bundle savedInstanceState) {
            handleInstanceState(savedInstanceState);
             setHasOptionsMenu(true);
               activity = getActivity();
                ButterKnife.bind(activity);

            if (rootView != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            } else {
                rootView = inflater.inflate(R.layout.movie_list,
                        container, false);
            }
                mProgressBar = ButterKnife.findById(rootView, R.id.progress);
                mRecyclerView = ButterKnife.findById(rootView, R.id.movie_recycler);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(activity);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

                loadMovies();

            swipeContainer = ButterKnife.findById(rootView, R.id.swipeContainer);
            // Setup refresh listener which triggers new data loading
            swipeContainer.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadMovies();
                }
            });
            swipeContainer.setColorSchemeResources(R.color.colorAccent);

            return rootView;
        }

    void loadMovies(){
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkEndpoints request = retrofit.create(NetworkEndpoints.class);
        //Async call to TMDB
        Call<Movies> call = request.getMovies();
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                swipeContainer.setRefreshing(false);
                final Movies movieResponse = response.body();

                //Check if results are null
                if (movieResponse != null) {
                    mAdapterItems = new ArrayList<>(movieResponse.getMovies());
                    mMovieAdapter = new MovieAdapter(mAdapterItems);
                    mRecyclerView.setAdapter(mMovieAdapter);

                ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        movie = mAdapterItems.get(position);

                        Intent intent = new Intent(activity, MovieDetailsActivity.class);
                        intent.putExtra("movie_id", movie.getId());
                        startActivity(intent);
                    }
                });
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<MovieResult>();
            mAdapterKeys = new ArrayList<>();
        }
    }
    // Saving the list of items and keys of the items on rotation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mMovieAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mMovieAdapter.getKeys());
    }

}

