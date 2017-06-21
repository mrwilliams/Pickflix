package mobi.kewi.pickflix.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mobi.kewi.pickflix.R;

/**
 * Created by Kevin on 10/06/17.
 */

public class PersonDetailsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.person_details_toolbar);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment mPersonDetailsFragment = new PersonDetailsFragment();
        fragmentTransaction.add(R.id.person_details_container, mPersonDetailsFragment);
        fragmentTransaction.commit();
    }
}

