package com.r0adkll.kiosk.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.session.model.Location;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by r0adkll on 8/23/14.
 */
public class HomeFragment extends Fragment {

    /***************************************************************************************
     *
     * Static Initializer
     *
     */

    /**
     * Create a new instance of this fragment
     *
     * @return the newly created instance
     */
    public static HomeFragment createInstance() {
        HomeFragment frag = new HomeFragment();
        return frag;
    }

    /***************************************************************************************
     *
     * Constants
     *
     */

    /***************************************************************************************
     *
     * Variables
     *
     */

    @InjectView(R.id.tab_scroller)
    HorizontalScrollView mTabScroller;
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.tabhost)
    TabHost mTabhost;



    /**
     * Empty Constructor
     */
    public HomeFragment() {
    }

    /**************************************************************************************
     *
     * Lifecycle Endpoints
     *
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Initialize Views
        initViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, layout);
        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /***************************************************************************************
     *
     * Helper Endpoints
     *
     */

    /**
     * Initialize the fragment's views
     */
    private void initViews() {

    }

    private void configureTabs(Location location){
        mTabhost.setup();



    }


    class DynamicPagerAdapter extends FragmentPagerAdapter{

        public DynamicPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {


            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }


}
