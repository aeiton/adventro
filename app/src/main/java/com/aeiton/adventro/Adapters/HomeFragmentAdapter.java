package com.aeiton.adventro.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aeiton.adventro.Fragments.LocationFragment;
import com.aeiton.adventro.Fragments.NearbyLocation;
import com.aeiton.adventro.Fragments.NewsFeedFragment;


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
                return LocationFragment.newInstance();
            case 3:
                return LocationFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
