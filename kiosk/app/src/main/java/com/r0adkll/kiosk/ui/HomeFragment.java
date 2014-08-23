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
import android.widget.ListView;
import android.widget.TabHost;

import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.adapters.ContentListAdapter;
import com.r0adkll.kiosk.session.APIHandler;
import com.r0adkll.kiosk.session.UserSession;
import com.r0adkll.kiosk.session.model.Content;
import com.r0adkll.kiosk.session.model.Location;
import com.r0adkll.kiosk.util.TabFactory;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * ************************************************************************************
     * <p/>
     * Variables
     */

    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.tabhost)
    TabHost mTabhost;

    private Location mCurrentLocation;


    /**
     * Empty Constructor
     */
    public HomeFragment() {
    }

    /**
     * ***********************************************************************************
     * <p/>
     * Lifecycle Endpoints
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

        Location test = UserSession.getSession().getLocation(2);
        configureLocation(test);

    }

    private void configureLocation(Location location) {
        mCurrentLocation = location;

        if(mTabhost.getTabWidget() != null)
            mTabhost.clearAllTabs();

        mTabhost.setup();

        // Iterate through location categories and determine the tabs to setup
        for (String category : location.categories) {

            // Initialize the TabSpecs
            TabHost.TabSpec newTabSpec = mTabhost
                    .newTabSpec(category)
                    .setContent(new TabFactory(getActivity()))
                    .setIndicator(category);

            mTabhost.addTab(newTabSpec);
        }

        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(mCurrentLocation != null){
                    int index = mCurrentLocation.categories.indexOf(tabId);
                    mViewpager.setCurrentItem(index, true);
                }
            }
        });


        // Now configure the Pager
        mViewpager.setAdapter(new CategoryPagerAdapter(getChildFragmentManager(), location));
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                mTabhost.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    class CategoryPagerAdapter extends FragmentPagerAdapter {

        private Location location;

        public CategoryPagerAdapter(FragmentManager fm, Location location) {
            super(fm);
            this.location = location;
        }

        @Override
        public Fragment getItem(int i) {
            String category = location.categories.get(i);
            return CategoryFragment.createInstance(location, category);
        }

        @Override
        public int getCount() {
            return location.categories.size();
        }
    }

    public static class CategoryFragment extends Fragment {

        public static CategoryFragment createInstance(Location location, String category) {
            CategoryFragment frag = new CategoryFragment();
            Bundle args = new Bundle();
            args.putString("category", category);
            args.putLong("location", location.id);
            frag.setArguments(args);
            return frag;
        }

        /***************************************************************************************
         *
         * Constants
         *
         */

        /**
         * ************************************************************************************
         * <p/>
         * Variables
         */

        @InjectView(R.id.list)
        ListView mList;

        private long mLocationId;
        private String mCategory;

        private List<Content> mContent;
        private ContentListAdapter mAdapter;

        /**
         * Empty Constructor
         */
        public CategoryFragment() {
        }

        /**
         * ************************************************************************************
         * <p/>
         * Lifecycle Endpoints
         */

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // Pull arguments
            Bundle args = getArguments();
            if (args != null) {
                mLocationId = args.getLong("location");
                mCategory = args.getString("category");
            }

            // Initialize Views
            initViews();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_category, container, false);
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

            loadData();

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
        private void initViews() {

            mContent = new ArrayList<>();
            mAdapter = new ContentListAdapter(getActivity(), mContent);
            mList.setAdapter(mAdapter);

        }

        /**
         * Load the content data for this category and location
         */
        private void loadData(){

            UserSession.getSession().getContent(mLocationId, mCategory, new APIHandler<List<Content>>() {
                @Override
                public void onSuccess(List<Content> data) {
                    mContent.clear();
                    mContent.addAll(data);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Throwable err, int code, String message) {

                }
            });

        }


    }

}
