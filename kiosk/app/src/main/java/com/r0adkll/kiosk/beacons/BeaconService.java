package com.r0adkll.kiosk.beacons;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.r0adkll.kiosk.session.UserSession;
import com.r0adkll.kiosk.session.model.ActiveBeacon;
import com.r0adkll.kiosk.util.DoubleDecker;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by r0adkll on 8/23/14.
 */
public class BeaconService extends Service implements BeaconManager.RangingListener {

    private static final long BEACON_SCAN_PERIOD = 5000L;
    private static final long BEACON_WAIT_PERIOD = 25000L;
    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

    private SharedPreferences mPrefs;
    private NotificationManager mNotifMan;
    private BeaconManager mBeaconManager;
    private Beacon mActiveBeacon;

    @Override
    public void onCreate() {
        super.onCreate();

        mPrefs = getSharedPreferences(UserSession.PREFERENCE_GENERIC_NAME, Context.MODE_PRIVATE);
        mNotifMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        DoubleDecker.getBus().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initializeEstimote();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DoubleDecker.getBus().unregister(this);

        try {
            mBeaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mBeaconManager.disconnect();

    }



    /**
     * Initialize the Estimote Monitoring to check if a device enters the area of
     * a selected Beacon to notify the Harvest time tracking
     */
    private void initializeEstimote() {

        // Initialize the Beacon manager
        mBeaconManager = new BeaconManager(this);
        mBeaconManager.setBackgroundScanPeriod(BEACON_SCAN_PERIOD, BEACON_WAIT_PERIOD);
        mBeaconManager.setRangingListener(this);

        // Connect to the Beacon Service
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    mBeaconManager.startRanging(ALL_ESTIMOTE_BEACONS);

                    Timber.i("Starting Ranging");
                } catch (RemoteException e) {
                    Timber.d("Error while starting monitoring");
                }
            }
        });
    }

    @Override
    public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
        if(!beacons.isEmpty()) {

            List<Beacon> competeing = new ArrayList<>();
            for (Beacon beacon : beacons) {
                Timber.i("Beacon [%s][%s][%.2fm][%s][RSSI: %d]", beacon.getName(), beacon.getMacAddress(), Utils.computeAccuracy(beacon), Utils.computeProximity(beacon), beacon.getRssi());

                if (mActiveBeacon == null) {
                    mActiveBeacon = beacon;
                    DoubleDecker.getBus().post(new ActiveBeacon(mActiveBeacon));
                } else {
                    if (mActiveBeacon.getMacAddress().equals(beacon.getMacAddress())) {
                        mActiveBeacon = beacon;
                    } else {
                        competeing.add(beacon);
                    }
                }
            }

            // Iterate through the beacons that are competeing for activeness
            for (Beacon beacon : competeing) {
                if (beacon.getRssi() > mActiveBeacon.getRssi()) {
                    // the scanned beacon that isn't active is stronger than the active one. Check accuracy
                    double activeAccu = Utils.computeAccuracy(mActiveBeacon);
                    double competeAccu = Utils.computeAccuracy(beacon);

                    if (competeAccu < activeAccu) {

                        Timber.i("New Active Beacon: " + beacon.getMacAddress());

                        // Confirmed take over
                        mActiveBeacon = beacon;
                        DoubleDecker.getBus().post(new ActiveBeacon(mActiveBeacon));
                        break;

                    }
                }
            }

        }else{
            DoubleDecker.getBus().post(new ActiveBeacon(null));
        }

    }

    @Produce
    public ActiveBeacon provideActiveBeacon(){
        if(mActiveBeacon != null)
            return new ActiveBeacon(mActiveBeacon);

        return null;
    }

}