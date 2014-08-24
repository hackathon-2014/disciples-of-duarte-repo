package com.r0adkll.kiosk.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.adapters.SongListAdapter;
import com.r0adkll.kiosk.session.model.Content;
import com.r0adkll.kiosk.session.model.Song;

import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by r0adkll on 8/23/14.
 */
public class MusicDetailActivity extends Activity {


    @InjectView(R.id.short_description)
    TextView mShortDescription;

    @InjectView(R.id.playlist)
    ListView mPlaylist;

    @InjectView(R.id.banner)
    ImageView mBanner;

    @InjectView(R.id.title)
    TextView mTitle;

    @InjectView(R.id.watch_now)
    TextView mWatchNow;


    private List<Song> mSongs;
    private SongListAdapter mAdapter;

    private Content mContent;
    private FadingActionBarHelper mFadingHelper;

    private MediaPlayer mPlayer;
    private int currentSong = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setIcon(R.drawable.ic_action_hyper);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            mContent = getIntent().getParcelableExtra("content");
        }

        if (savedInstanceState != null) {
            mContent = getIntent().getParcelableExtra("content");
        }

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background)
                .headerLayout(R.layout.layout_detail_header)
                .contentLayout(R.layout.activity_detail_music)
                .lightActionBar(true);

        setContentView(mFadingHelper.createView(this));
        ButterKnife.inject(this);
        ButterKnife.inject(this);
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
        switch (item.getItemId()) {
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

        ImageLoader.getInstance().displayImage(mContent.metadata.optString("banner"), mBanner);

        // Fill out the info section from meta-data
        mWatchNow.setText("LISTEN NOW");
        mWatchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



}
