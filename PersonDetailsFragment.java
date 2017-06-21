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

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.Models.KnownFor;
import mobi.kewi.pickflix.Models.PersonInfo;
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

public class PersonDetailsFragment extends Fragment {

    final public String IMAGE_URL = "http://image.tmdb.org/t/p/w500/";
    final public String PERSON_URL = "https://api.themoviedb.org/3/person/";

    public static final String ARG_ITEM_ID = "index";

    public static PersonDetailsFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, index);
        PersonDetailsFragment fragment = new PersonDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private Integer personId;
    private View rootView;
    FragmentActivity activity;
    private ArrayList<KnownFor> known_for_array;
    int i = 0;

    @BindView(R.id.person_known_for) TextView person_known_for;
    @BindView(R.id.person_details_toolbar_layout)CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.person_homepage)Button person_homepage;
    @BindView(R.id.backdrop_image)ImageView backdrop_image;
    @BindView(R.id.person_name) TextView person_name;
    @BindView(R.id.person_bio)TextView person_bio;
 //   @BindView(R.id.person_birthday)TextView person_birthday;
    @BindView(R.id.person_birth_place)TextView person_birth_place;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        activity = getActivity();
        Intent intent = activity.getIntent();
        ButterKnife.bind(activity);

        personId = intent.getIntExtra("person_info_id", 0);
        getPersonInfo(personId);

        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        } else {
            rootView = inflater.inflate(R.layout.activity_person_details,
                    container, false);

            collapsingToolbarLayout = ButterKnife.findById(rootView, R.id.person_details_toolbar_layout);
            collapsingToolbarLayout.setTitleEnabled(true);
            collapsingToolbarLayout.setTitle("Pickflix");
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.AppTheme);
            collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

            String personName = intent.getStringExtra("person_name");
            String personImage = intent.getStringExtra("person_profile_path");
            String personKnownFor = intent.getStringExtra("person_known_for");

            person_known_for = ButterKnife.findById(rootView, R.id.person_known_for);
            person_known_for.setText(personKnownFor);

            // Define views and set the elements of the details page
            person_name = ButterKnife.findById(rootView, R.id.person_name);
            person_name.setText(personName);
        }
        return rootView;
    }

    private void getPersonInfo(Integer personId) {

        String persondetailsCall = personId+"?language=en-US&api_key=ab5b7a43e943cb2cb68881139eda4012";
        Log.i("Retrofit", "URL: "+persondetailsCall);
        //Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PERSON_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkEndpoints personRequest = retrofit.create(NetworkEndpoints.class);
        //Async call to TMDB
        Call<PersonInfo> callPerson = personRequest.getPersonInfo(persondetailsCall);
        callPerson.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> callPerson, Response<PersonInfo> response) {
                final PersonInfo personInfoResponse = response.body();
                if (personInfoResponse != null) {

                    String personBio = personInfoResponse.getBiography();
                    person_bio = ButterKnife.findById(rootView, R.id.person_bio);
                    person_bio.setText(personBio);

                    String personBirthPlace = personInfoResponse.getPlaceOfBirth();
                    person_birth_place = ButterKnife.findById(rootView, R.id.person_birth_place);
                    person_birth_place.setText("Born:" +personBirthPlace);

                    String backdropImage = personInfoResponse.getProfilePath();
                    backdrop_image = ButterKnife.findById(rootView, R.id.backdrop_image);
                    String backdrop_url = (IMAGE_URL + backdropImage);
                    Picasso.with(getContext()).load(backdrop_url).into(backdrop_image);

                    String personHomepage = personInfoResponse.getHomepage();
                    person_homepage = ButterKnife.findById(rootView, R.id.person_homepage);
                    final Uri uri = Uri.parse(personHomepage);

                    person_homepage.setOnClickListener(new View.OnClickListener() {
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
            public void onFailure(Call<PersonInfo> callPerson, Throwable t) {

            }
        });
    }
}