package com.r0adkll.kiosk.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class DetailActivity extends Activity {


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
    public DetailActivity() {
    }


    /**
     * ************************************************************************************
     * <p/>
     * Lifecycle Endpoints
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(R.drawable.ic_action_hyper);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent() != null){
            mContent = getIntent().getParcelableExtra("content");
        }

        if(savedInstanceState != null){
            mContent = getIntent().getParcelableExtra("content");
        }

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.layout_detail_header)
                .contentLayout(R.layout.fragment_detail)
                .lightActionBar(true);

        setContentView(mFadingHelper.createView(this));
        ButterKnife.inject(this);

        mFadingHelper.initActionBar(this);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                // Some Share action


                return true;
        }

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("content", mContent);
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
        mShortDescription.setText(mContent.summary);
        mFullDescription.setText(mContent.description);

        ImageLoader.getInstance().displayImage(mContent.metadata.optString("banner"), mBanner);

        // Fill out the info section from meta-data
        switch (mContent.type){
            case Content.MOVIE:
                mWatchNow.setText("WATCH NOW");

                int seconds = mContent.metadata.optInt("length", 0);
                int minutes = seconds / 60;

                mInfo1.setText(minutes + " MIN");
                mInfo1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_runtime), null, null, null);

                String mpaa = mContent.metadata.optString("mpaa", "N/A");
                mInfo2.setText(mpaa);
                mInfo2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_audience), null, null, null);

                String date = mContent.metadata.optString("date", "N/A");
                mInfo3.setText(date);
                mInfo3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_release), null, null, null);

                String rating = mContent.metadata.optString("imdb_rating");
                mInfo4.setText(rating);
                mInfo4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_rating), null, null, null);

                break;
            case Content.MUSIC:
                mWatchNow.setText("PLAY NOW");


                break;
            case Content.TVSHOW:


                break;
            case Content.MAGAZINE:
                mWatchNow.setText("READ NOW");

                String author = mContent.metadata.optString("author");
                mInfo1.setText(author);
                mInfo1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_author), null, null, null);

                int pages = mContent.metadata.optInt("page_count", 0);
                mInfo2.setText(String.format("%d PAGES", pages));
                mInfo2.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_pages), null, null, null);

                String bookDate = mContent.metadata.optString("date", "N/A");
                mInfo3.setText(bookDate);
                mInfo3.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_release), null, null, null);

                String bookRating = mContent.metadata.optString("rating");
                mInfo4.setText(bookRating);
                mInfo4.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_action_rating), null, null, null);

                break;
        }

        mWatchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /***************************************************************************************
     *
     * Inner classes and interfaces
     *
     */

}
