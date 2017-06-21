package mobi.kewi.pickflix.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Locale;

/**
 * Created by Kevin on 09/06/17.
 */

public class MainPagerAdapter extends SmartFragmentStatePagerAdapter {
    private String tabTitles[] = new String[]{"Movies", "TV Shows", "People"};
    int mNumOfTabs;
    int NUM_ITEMS = 3;

    public MainPagerAdapter(FragmentManager fm, int NUM_ITEMS) {
        super(fm);
        this.mNumOfTabs = NUM_ITEMS;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MovieListFragment();
            case 1:
                return new TvListFragment();
            case 2:
                return new PeopleListFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        Locale l = Locale.getDefault();
        return tabTitles[position];
    }

    /*
     * A placeholder fragment containing a simple view.
     */
    public static class MainHolderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_PAGE = "page";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static Fragment newInstance(int page) {
            Fragment fragment = new MainHolderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            fragment.setArguments(args);
            return fragment;
        }
    }
}