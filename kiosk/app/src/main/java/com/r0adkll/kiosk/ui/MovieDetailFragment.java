package com.r0adkll.kiosk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.session.model.Content;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by r0adkll on 8/23/14.
 */
public class MovieDetailFragment extends Fragment {

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
    public static MovieDetailFragment createInstance(Content content) {
        MovieDetailFragment frag = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("content", content);
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
    @InjectView(R.id.banner)
    ImageView mBanner;
    @InjectView(R.id.title)
    TextView mTitle;
    @InjectView(R.id.watch_now)
    TextView mWatchNow;
    @InjectView(R.id.short_description)
    TextView mShortDescription;
    @InjectView(R.id.info1)
    TextView mInfo1;
    @InjectView(R.id.info2)
    TextView mInfo2;
    @InjectView(R.id.info3)
    TextView mInfo3;
    @InjectView(R.id.info4)
    TextView mInfo4;
    @InjectView(R.id.full_description)
    TextView mFullDescription;

    private Content mContent;
    private FadingActionBarHelper mFadingHelper;

    /**
     * Empty Constructor
     */
    public MovieDetailFragment() {
    }


    /**
     * ************************************************************************************
     * <p/>
     * Lifecycle Endpoints
     */


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.layout_detail_header)
                .contentLayout(R.layout.fragment_detail)
                .lightActionBar(true);

        mFadingHelper.initActionBar(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = new Bundle();
        if(args != null){
            mContent = args.getParcelable("content");
        }

        // Initialize Views
        initViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = mFadingHelper.createView(inflater);
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
    private void initViews() {

        mTitle.setText(mContent.name);
        mShortDescription.setText(mContent.description);
        mFullDescription.setText(mContent.description);

        ImageLoader.getInstance().displayImage(mContent.metadata.optString("banner"), mBanner);

        // Fill out the info section from meta-data
        switch (mContent.type){
            case Content.MOVIE:

                int seconds = mContent.metadata.optInt("length", 0);
                int minutes = seconds / 60;

                mInfo1.setText(minutes + " MINUTES");
                mInfo1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_runtime), null, null, null);

                String mpaa = mContent.metadata.optString("mpaa", "N/A");
                mInfo2.setText(mpaa);
                mInfo2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_audience), null, null, null);

                String date = mContent.metadata.optString("date", "N/A");
                mInfo3.setText(date);
                mInfo3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_release), null, null, null);

                String rating = mContent.metadata.optString("rt_rating");
                mInfo4.setText(rating);
                mInfo4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_rating), null, null, null);

                break;
            case Content.MUSIC:

                break;
            case Content.TVSHOW:

                break;
            case Content.MAGAZINE:

                break;
        }

    }

    /***************************************************************************************
     *
     * Inner classes and interfaces
     *
     */

}
