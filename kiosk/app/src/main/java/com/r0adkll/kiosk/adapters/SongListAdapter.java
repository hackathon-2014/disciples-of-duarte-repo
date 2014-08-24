package com.r0adkll.kiosk.adapters;

import android.content.Context;
import android.view.View;

import com.r0adkll.deadskunk.adapters.BetterListAdapter;
import com.r0adkll.kiosk.session.model.Song;

import java.util.List;

/**
 * Created by r0adkll on 8/23/14.
 */
public class SongListAdapter extends BetterListAdapter<Song> {


    public SongListAdapter(Context context, int textViewResourceId, List<Song> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return null;
    }

    @Override
    public void bindHolder(ViewHolder viewHolder, int i, Song song) {

    }
}
