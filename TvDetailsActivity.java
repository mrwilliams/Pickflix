package mobi.kewi.pickflix.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import mobi.kewi.pickflix.R;

/**
 * Created by Kevin on 10/06/17.
 */

public class TvDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tv_details_toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mTvDetailsFragment = new TvDetailsFragment();
        fragmentTransaction.add(R.id.tv_details_container, mTvDetailsFragment);
        fragmentTransaction.commit();
    }
}
