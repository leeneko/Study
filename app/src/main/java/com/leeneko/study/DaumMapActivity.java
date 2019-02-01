package com.leeneko.study;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DaumMapActivity extends AppCompatActivity {

    private double mLat, mLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daum_map);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 현재 위치를 알아낼 때 GPS, 네트워크 둘 중 하나를 사용
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            mLat = location.getLatitude(); // 위도
            mLon = location.getLongitude(); // 경도
            Log.d("ffff", "lat: " + mLat + " lon: " + mLon);
        }
    }
}
