package com.r0adkll.kiosk.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

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
     * @return      the newly created instance
     */
    public static HomeFragment createInstance(){
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

    /**
     * Empty Constructor
     */
    public HomeFragment(){}

    /***************************************************************************************
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
        View layout = inflater.inflate(0, container, false);
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

    /***************************************************************************************
     *
     * Helper Endpoints
     *
     */

    /**
     * Initialize the fragment's views
     */
    private void initViews(){



    }

}
