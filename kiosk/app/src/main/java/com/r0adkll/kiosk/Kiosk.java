package com.r0adkll.kiosk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.r0adkll.kiosk.beacons.BeaconService;
import com.r0adkll.kiosk.session.UserSession;
import com.r0adkll.kiosk.ui.HomeFragment;


public class Kiosk extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);
        getActionBar().setIcon(R.drawable.ic_action_hyper);

        initKiosk();

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.createInstance())
                    .commit();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kiosk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }



    private void initKiosk(){

        UserSession.getSession().initialize(this);

        // Launch the ranging service
        Intent beaconService = new Intent(this, BeaconService.class);
        startService(beaconService);


    }

}
