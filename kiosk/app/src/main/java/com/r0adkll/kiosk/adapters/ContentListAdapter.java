package com.r0adkll.kiosk.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.r0adkll.deadskunk.adapters.BetterListAdapter;
import com.r0adkll.kiosk.R;
import com.r0adkll.kiosk.session.model.Content;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by r0adkll on 8/23/14.
 */
public class ContentListAdapter extends BetterListAdapter<Content> {

    public ContentListAdapter(Context context, List<Content> objects) {
        super(context, R.layout.layout_content_item, objects);
    }

    @Override
    public ViewHolder createHolder(View view) {
        ContentViewHolder holder = new ContentViewHolder(view);

        return holder;
    }

    @Override
    public void bindHolder(ViewHolder viewHolder, int i, Content content) {
        ContentViewHolder holder = (ContentViewHolder) viewHolder;

        holder.mTitle.setText(content.name);
        holder.mInfo.setText(content.type);
        holder.mDescription.setText(content.);


    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'layout_content_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Inmite Developers (http://inmite.github.io)
     */
    static class ContentViewHolder extends ViewHolder {
        @InjectView(R.id.poster)
        ImageView mPoster;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.info)
        TextView mInfo;
        @InjectView(R.id.description)
        TextView mDescription;

        ContentViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

