package com.aeiton.adventro.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aeiton.adventro.Fragments.NearbyLocation;
import com.aeiton.adventro.Fragments.NewsFeedFragment;
import com.aeiton.adventro.Fragments.PostsFragment;

/**
 * Created by User on 10-Jan-17.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NewsFeedFragment.newInstance();
            case 1:
                return NearbyLocation.newInstance();
            case 2:
                return PostsFragment.newInstance();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
