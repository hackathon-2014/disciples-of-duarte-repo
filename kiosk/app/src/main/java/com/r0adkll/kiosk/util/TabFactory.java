package com.r0adkll.kiosk.util;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/**
 * Empty TabFactory to provide empty views to allow the
 * view pager to work with the tabhost
 */
public class TabFactory implements TabHost.TabContentFactory{
    private final Context mCtx;

    public TabFactory(Context ctx){
        mCtx = ctx;
    }

    /**
     * Callback to make the tab contents
     *
     * @param tag Which tab was selected.
     * @return The view to display the contents of the selected tab.
     */
    @Override
    public View createTabContent(String tag) {
        View v = new View(mCtx);
        v.setMinimumHeight(0);
        v.setMinimumWidth(0);
        return v;
    }
}