package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import mobi.kewi.pickflix.R;

import static mobi.kewi.pickflix.R.id.toolbar;


public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.movie_details_toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mMovieDetailsFragment = new MovieDetailsFragment();
        fragmentTransaction.add(R.id.movie_details_container, mMovieDetailsFragment);
        fragmentTransaction.commit();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Share app
        int id = item.getItemId();
        if (id == R.id.ic_action_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}

