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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.ItemClickSupport;
import mobi.kewi.pickflix.Models.TvResult;
import mobi.kewi.pickflix.Models.TvShows;
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

public class TvListFragment extends Fragment {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    final public int LIMIT = 100;
    final public String TV_URL = "https://api.themoviedb.org/3/tv/";
    final public int CACHETIME = 432000; // 5days
    private static final String ARG_PAGE = "ARG_PAGE";
    private LinearLayoutManager mLayoutManager;
    private FragmentActivity activity;
    private ArrayList<TvResult> mAdapterItems;
    private ArrayList<String> mAdapterKeys;
    private TvAdapter mTvAdapter;
    private View rootView;
    private TvResult tvShow;

    public static TvListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TvListFragment fragment = new TvListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //  @BindView(R.id.progress) ProgressBar mProgressBar;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;
    @BindView(R.id.tv_recycler) RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        handleInstanceState(savedInstanceState);
            setHasOptionsMenu(true);
                activity = getActivity();
                    ButterKnife.bind(activity);
        loadTvShows();

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.tv_list,
                    container, false);
        }
  //      mProgressBar = ButterKnife.findById(rootView, R.id.progress);
        mRecyclerView = ButterKnife.findById(rootView, R.id.tv_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));


        swipeContainer = ButterKnife.findById(rootView, R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTvShows();
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorAccent);

        return rootView;
    }

    void loadTvShows(){
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TV_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i("Retrofit", "URL: "+TV_URL);
        final NetworkEndpoints request = retrofit.create(NetworkEndpoints.class);
        //Async call to TMDB
        Call<TvShows> call = request.getTvResults();
        call.enqueue(new Callback<TvShows>() {
            @Override
            public void onResponse(Call<TvShows> call, Response<TvShows> response) {

                swipeContainer.setRefreshing(false);
                final TvShows tvResponse = response.body();

                Log.i("TvShows ", "tvResponse total: "+response.body().getTotalResults());
                //Check if results are null
                if (tvResponse != null) {
                    mAdapterItems = new ArrayList<>(tvResponse.getTvResults());
                    mTvAdapter = new TvAdapter(mAdapterItems);
                    mRecyclerView.setAdapter(mTvAdapter);

                    ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                            tvShow = mAdapterItems.get(position);

                            final Integer tvShowId = tvShow.getId();
                            Log.i("TvShows ", "tvShowId: "+tvShowId);

                            Intent infoIntent = new Intent (activity, TvDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("tv_show_country", Parcels.wrap(tvShow.getOriginCountry()));
                            infoIntent.putExtra("tv_show_id", tvShowId);
                            infoIntent.putExtra("tv_show_name", tvShow.getName());
                            infoIntent.putExtra("tv_air_date", tvShow.getFirstAirDate());
                            infoIntent.putExtra("tv_overview",tvShow.getOverview());
                            infoIntent.putExtra("tv_backdrop",tvShow.getBackdropPath());
                            startActivity(infoIntent);
                        }
                    });
                }
                else {
                    Toast.makeText(activity, "No info provided", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvShows> call, Throwable t) {

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
            mAdapterItems = new ArrayList<TvResult>();
            mAdapterKeys = new ArrayList<>();
        }
    }
    // Saving the list of items and keys of the items on rotation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mTvAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mTvAdapter.getKeys());
    }
}