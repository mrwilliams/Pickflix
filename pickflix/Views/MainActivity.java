package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobi.kewi.pickflix.Networking.NetworkEndpoints;
import mobi.kewi.pickflix.R;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_ITEMS = 3;
    private ProgressBar progressBar;
    private FragmentManager fm;
    private NetworkEndpoints service;

    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.container)ViewPager mViewPager;
    @BindView(R.id.toolbar)Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        //File cacheFile = new File(getCacheDir(), "responses");

        toolbar = ButterKnife.findById(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        // Check whether viewPager is null
        mViewPager = ButterKnife.findById(this, R.id.container);
        if (mViewPager != null) {
            fm = getSupportFragmentManager();
            mViewPager.setAdapter(new MainPagerAdapter(fm, NUM_ITEMS
            ));
        }

        // Give the TabLayout the ViewPager
        tabLayout = ButterKnife.findById(this,R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
        }

        // Adapter that will return a fragment for each of the three
        // primary sections of the activity.
        final MainPagerAdapter mMainPagerAdapter;
        mMainPagerAdapter = new MainPagerAdapter(
                getSupportFragmentManager(), NUM_ITEMS);

        // When swiping between different sections, select the corresponding
        // tab.
        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    mMainPagerAdapter.getItem(position);
                }
            });
        }
    }


    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.ic_action_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}