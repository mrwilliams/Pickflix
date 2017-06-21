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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.ItemClickSupport;
import mobi.kewi.pickflix.Models.People;
import mobi.kewi.pickflix.Models.KnownFor;
import mobi.kewi.pickflix.Models.PersonResult;
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

public class PeopleListFragment extends Fragment {

    private final static String SAVED_ADAPTER_ITEMS = "SAVED_ADAPTER_ITEMS";
    private final static String SAVED_ADAPTER_KEYS = "SAVED_ADAPTER_KEYS";

    final public int LIMIT = 100;
    final public String PERSON_URL = "https://api.themoviedb.org/3/person/";
    final public int CACHETIME = 432000; // 5days
    private static final String ARG_PAGE = "ARG_PAGE";
    private LinearLayoutManager mLayoutManager;
    private FragmentActivity activity;
    private ArrayList<PersonResult> mAdapterItems;
    private ArrayList<String> mAdapterKeys;
    private PeopleAdapter mPeopleAdapter;
    private View rootView;
    private PersonResult person;
    int i = 0;

    public static PeopleListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PeopleListFragment fragment = new PeopleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @BindView(R.id.progress)
//    ProgressBar mProgressBar;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.people_recycler)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        handleInstanceState(savedInstanceState);
        activity = getActivity();
        ButterKnife.bind(activity);

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.people_list,
                    container, false);
        }
    //    mProgressBar = ButterKnife.findById(rootView, R.id.progress);
        mRecyclerView = ButterKnife.findById(rootView, R.id.people_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

        loadPeople();

        swipeContainer = ButterKnife.findById(rootView, R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPeople();
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorAccent);

        return rootView;
    }

    void loadPeople(){
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PERSON_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkEndpoints peopleRequest = retrofit.create(NetworkEndpoints.class);
        //Async call to TMDB
        Call<People> callPeople = peopleRequest.getPersonResults();
        callPeople.enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> callPeople, Response<People> response) {

                swipeContainer.setRefreshing(false);
                final People peopleResponse = response.body();
                Log.i("Retrofit", "People size: "+peopleResponse.getPersonResults().size());

                //Check if results are null
                if (peopleResponse != null) {
                    mAdapterItems = new ArrayList<>(peopleResponse.getPersonResults());
                    mPeopleAdapter = new PeopleAdapter(mAdapterItems);
                    mRecyclerView.setAdapter(mPeopleAdapter);

                    ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                            person = mAdapterItems.get(position);

                            Object[] personKnownForArray = person.getKnownFor().toArray();
                            String[] personKnownFor = new String[(person.getKnownFor()).size()];

                            for (Object o : personKnownForArray) {
                                KnownFor known = person.getKnownFor().get(i);
                                personKnownFor[i] = known.getTitle();
                                i++;
                            }
                            String knownFor = TextUtils.join(", ", personKnownFor);
                            Log.i("PeopleList", "knownFor: "+knownFor);
                            final Integer personId = person.getId();
                            Intent personIntent = new Intent (activity, PersonDetailsActivity.class);
                            personIntent.putExtra("person_info_id", personId);
                            personIntent.putExtra("person_profile_path", person.getProfilePath());
                            personIntent.putExtra("person_known_for", knownFor);
                            personIntent.putExtra("person_name", person.getName());
                            startActivity(personIntent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<People> callPeople, Throwable t) {

            }
        });

    }

    // Saving the list of items and keys of the items on rotation
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS, Parcels.wrap(mPeopleAdapter.getItems()));
        outState.putStringArrayList(SAVED_ADAPTER_KEYS, mPeopleAdapter.getKeys());
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS)) {
            mAdapterItems = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS));
            mAdapterKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS);
        } else {
            mAdapterItems = new ArrayList<PersonResult>();
            mAdapterKeys = new ArrayList<>();
        }
    }

}
