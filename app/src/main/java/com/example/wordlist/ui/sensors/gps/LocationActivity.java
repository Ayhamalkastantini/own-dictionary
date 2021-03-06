package com.example.wordlist.ui.sensors.gps;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.wordlist.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * The class Location activity extends application compat activity
 */
public class LocationActivity extends AppCompatActivity {

    private LocationManager locationMangaer = null;
    private LocationListener locationListener = null;

    private Button btnGetLocation = null;
    private EditText editLocation = null;
    private ProgressBar pb = null;

    private static final String TAG = "Debug";
    private Boolean flag = false;

    @Override

/**
 *
 * On create
 *
 * @param savedInstanceState  the saved instance state
 */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);


        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        editLocation = (EditText) findViewById(R.id.editTextLocation);
        btnGetLocation = (Button) findViewById(R.id.btnLocation);
        setTitle("Get Current location");
        locationMangaer = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        btnGetLocation.setOnClickListener(view -> {
            pb.setVisibility(View.VISIBLE);
            locationListener = new MyLocationListener();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                alertbox("GPS Status!!", "Your GPS is: OFF");
            }else{
                locationMangaer.requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 5000, 10, locationListener);
            }
        });
    }


    /*----------Method to create an AlertBox ------------- */
    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {

                            /**
                             *
                             * On click
                             *
                             * @param dialog  the dialog
                             * @param id  the id
                             */
                            public void onClick(DialogInterface dialog, int id) {

                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            /**
                             *
                             * On click
                             *
                             * @param dialog  the dialog
                             * @param id  the id
                             */
                            public void onClick(DialogInterface dialog, int id) {

                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*----------Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {
        @Override

/**
 *
 * On location changed
 *
 * @param loc  the loc
 */
        public void onLocationChanged(Location loc) {


            editLocation.setText("");
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(),"Location changed : Lat: " +
                            loc.getLatitude()+ " Lng: " + loc.getLongitude(),
                    Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " +loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " +loc.getLatitude();
            Log.v(TAG, latitude);

            /*----------to get City-Name from coordinates ------------- */
            String cityName= null;
            Geocoder gcd = new Geocoder(getBaseContext(),
                    Locale.getDefault());
            List<Address>  addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(), loc
                        .getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName= addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String s = longitude+"\n"+latitude +
                    "\n\nMy Currrent City is: " + cityName;
            editLocation.setText(s);
            /********
             * I have the same issue. When you test your app in AVD map will show google corporation as your place.
             * But if your code is correct, when you test the app in your device, you will get your current location correctly.
             * https://stackoverflow.com/questions/60483232/current-location-is-not-showing-in-my-android-app
             */
        }

        @Override
        public void onProviderDisabled(String provider) {

            // TODO Auto-generated method stub
        }

        @Override

/**
 *
 * On provider enabled
 *
 * @param provider  the provider
 */
        public void onProviderEnabled(String provider) {

            // TODO Auto-generated method stub
        }

        @Override


/**
 *
 * On status changed
 *
 * @param provider  the provider
 * @param int  the int
 * @param extras  the extras
 */
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {

            // TODO Auto-generated method stub
        }
    }
}
