package com.aeiton.adventro.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aeiton.adventro.Activity.HomeActivity;
import com.aeiton.adventro.Adapters.HomeFragmentAdapter;
import com.aeiton.adventro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserHomeFragment extends Fragment {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private HomeFragmentAdapter mStudentsHomeFragmentAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    int unselectedIcon[] = {R.drawable.ic_homes_unselected, R.drawable.ic_locations_unselected, R.drawable.ic_posts_unselected};
    int selectedIcon[] = {R.drawable.ic_home_selected, R.drawable.ic_location_selected, R.drawable.ic_posts_selected};

    public UserHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_home, container, false);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mStudentsHomeFragmentAdapter = new HomeFragmentAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mStudentsHomeFragmentAdapter);
        mViewPager.setOffscreenPageLimit(3);

        final TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        //setting the first tab as selected
        tabLayout.getTabAt(0).setIcon(selectedIcon[0]);

        //initially setting unselected icon for all the tabs
        for (int i = 1; i < 3; i++) {
            tabLayout.getTabAt(i).setIcon(unselectedIcon[i]);
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();


                //to show and hide fab on other tabs
                if (position == 0) {
                    ((HomeActivity) getActivity()).showFab();
                } else {
                    ((HomeActivity) getActivity()).hideFab();
                }


                //setting icons for each tab
                tab.setIcon(selectedIcon[position]);
//                HomeActivity.changeTitle(titles[position]);

                for (int i = 0; i < 3; i++) {
                    if (i != position) {
                        tabLayout.getTabAt(i).setIcon(unselectedIcon[i]);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;

    }


}
